package vst.science.ontologies.creation;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.jena.ontology.*;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.vocabulary.OWL2;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class OntologyBuilder implements IOWL2OntologyBuilder {
    private final OntModel model;
    private final String ns;

    public OntologyBuilder(String uri, OntModelSpec spec) {
        model = ModelFactory.createOntologyModel(spec);
        this.ns = uri + ".owl#";
        model.setNsPrefix("edu", ns);
    }

    private OntClass getClass(String classUri) {
        return Objects.requireNonNull(model.getOntClass(ns + classUri));
    }

    private ObjectProperty getObjectProperty(String objectPropertyUri) {
        return Objects.requireNonNull(model.getObjectProperty(ns + objectPropertyUri));
    }

    private DatatypeProperty getDatatypeProperty(String datatypePropertyUri) {
        return Objects.requireNonNull(model.getDatatypeProperty(ns + datatypePropertyUri));
    }

    private OntProperty getOntProperty(String propertyUri) {
        return Objects.requireNonNull(model.getOntProperty(ns + propertyUri));
    }

    private Individual getIndividual(String individualUri) {
        return Objects.requireNonNull(model.getIndividual(ns + individualUri));
    }

    private OntResource getResource(String resourceUri) {
        return Objects.requireNonNull(model.getOntResource(ns + resourceUri));
    }

    private OntClass addUnionClass(List<String> classesUri) {
        return model.createUnionClass(
                null,
                model.createList(classesUri.stream().map(this::getClass).iterator())
        );
    }


    @Override
    public IOWL2OntologyBuilder addClass(String classUri) {
        OntClass ontClass = model.createClass(ns + classUri);
        ontClass.setSuperClass(OWL2.Thing);
        return this;
    }

    @Override
    public IOWL2OntologyBuilder addClass(String classUri, String superClassUri) {
        OntClass ontClass = model.createClass(ns + classUri);
        ontClass.setSuperClass(getClass(superClassUri));
        return this;
    }

    @Override
    public IOWL2OntologyBuilder addObjectProperty(String objPropertyUri) {
        ObjectProperty property = model.createObjectProperty(ns + objPropertyUri);
        property.setSuperProperty(OWL2.topObjectProperty);
        return this;
    }

    @Override
    public IOWL2OntologyBuilder addObjectProperty(String objPropertyUri, String superObjPropertyUri) {
        ObjectProperty property = model.createObjectProperty(ns + objPropertyUri);
        property.setSuperProperty(getObjectProperty(superObjPropertyUri));
        return this;
    }

    @Override
    public IOWL2OntologyBuilder addInverseFunctionProperty(String objPropertyUri, String inversePropertyURI) {
        ObjectProperty property = getObjectProperty(objPropertyUri);
        ObjectProperty inverseProperty = getObjectProperty(inversePropertyURI);
        property.addInverseOf(inverseProperty);
        return this;
    }

    @Override
    public IOWL2OntologyBuilder addDatatypeProperty(String datatypePropertyUri) {
        DatatypeProperty property = model.createDatatypeProperty(ns + datatypePropertyUri);
        property.addSuperProperty(OWL2.topDataProperty);
        return this;
    }

    @Override
    public IOWL2OntologyBuilder addDatatypeProperty(String datatypePropertyUri, String superDatatypePropertyUri) {
        DatatypeProperty property = model.createDatatypeProperty(ns + datatypePropertyUri);
        property.addSuperProperty(getDatatypeProperty(superDatatypePropertyUri));
        return this;
    }

    @Override
    public IOWL2OntologyBuilder addRestrictions(String propertyUri, String domainUri, String rangeUri) {
        OntProperty property = getOntProperty(propertyUri);
        property.addDomain(getResource(domainUri));
        property.addRange(getResource(rangeUri));
        return this;
    }

    @Override
    public IOWL2OntologyBuilder addIndividual(String classUri, String individualUri) {
        OntClass ontClass = getClass(classUri);
        ontClass.createIndividual(ns + individualUri);
        return this;
    }

    @Override
    public IOWL2OntologyBuilder addIndividualProperty(String individualIri,
                                                      String propertyUri,
                                                      String resourceUri) {
        Individual individual = getIndividual(individualIri);
        individual.addProperty(getOntProperty(propertyUri), getResource(resourceUri));
        return this;
    }

    @Override
    public IOWL2OntologyBuilder addLabel(String resourceUri, Pair<String, String> label) {
        OntResource resource = getResource(resourceUri);
        resource.addLabel(label.getLeft(), label.getRight());
        return this;
    }

    @Override
    public IOWL2OntologyBuilder addLabels(String resourceUri, Map<String, String> labels) {
        OntResource resource = getResource(resourceUri);
        labels.forEach(resource::addLabel);
        return this;
    }

    @Override
    public IOWL2OntologyBuilder addCoveringTheorem(String superClassUri, List<String> subclassesUri) {
        OntClass ontClass = getClass(superClassUri);
        ontClass.addEquivalentClass(addUnionClass(subclassesUri));
        return this;
    }

    @Override
    public OntModel build() {
        return model;
    }
}
