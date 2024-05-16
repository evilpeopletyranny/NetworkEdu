package vst.science.ontologies.resoners;

import org.apache.jena.ontology.OntModel;

public interface IReasonerAdapter {
    OntModel applyReasoner(OntModel ontModel) throws Exception;
}
