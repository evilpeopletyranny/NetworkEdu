package vst.science.ontologies.utils.modifiers;

import org.apache.jena.ontology.ObjectProperty;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.RDFNode;
import vst.science.ontologies.utils.filters.OntFilter;

public class OntModifier {
    public static void addInferredInverseProperties(OntModel ontModel, InfModel infModel) {
        OntFilter.getFilteredProperty(ontModel, ObjectProperty::hasInverse, objectProperty -> objectProperty.getInverse().asObjectProperty())
                .forEach(property -> infModel.listStatements(null, property, (RDFNode) null)
                        .filterKeep(statement -> statement.getObject().isResource())
                        .forEach(statement -> ontModel.add(statement.getSubject(), statement.getPredicate(), statement.getObject()))
                );

    }
}
