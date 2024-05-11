package vst.science.ontologies.creation;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.jena.ontology.OntModel;

import java.util.List;
import java.util.Map;

public interface IOWL2OntologyBuilder {
    IOWL2OntologyBuilder addClass(String classUri);

    IOWL2OntologyBuilder addClass(String classUri, String superClassUri);

    IOWL2OntologyBuilder addObjectProperty(String objPropertyUri);

    IOWL2OntologyBuilder addObjectProperty(String objPropertyUri, String superObjPropertyUri);

    IOWL2OntologyBuilder addInverseFunctionProperty(String objPropertyUri, String inversePropertyURI);

    IOWL2OntologyBuilder addDatatypeProperty(String datatypePropertyUri);

    IOWL2OntologyBuilder addDatatypeProperty(String datatypePropertyUri, String superDatatypePropertyUri);

    IOWL2OntologyBuilder addRestrictions(String propertyUri, String domainUri, String rangeUri);

    IOWL2OntologyBuilder addIndividual(String classUri, String individualUri);

    IOWL2OntologyBuilder addIndividualProperty(String individualUri, String propertyUri, String otherIndividualUri);

    IOWL2OntologyBuilder addLabel(String resourceUri, Pair<String, String> label);

    IOWL2OntologyBuilder addLabels(String resourceUri, Map<String, String> labels);

    IOWL2OntologyBuilder addCoveringTheorem(String superClassUri, List<String> subclassesUri);

    OntModel build();
}
