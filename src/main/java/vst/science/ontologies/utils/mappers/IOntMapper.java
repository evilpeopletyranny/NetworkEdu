package vst.science.ontologies.utils.mappers;

import org.apache.jena.ontology.OntModel;
import org.semanticweb.owlapi.model.OWLOntology;

public interface IOntMapper {
    OWLOntology ontModel2OWLontology(OntModel ontModel);

    OntModel owlOntology2OntModel(OWLOntology ontology);
}
