package vst.science.ontologies.experiments;

import org.apache.jena.ontology.OntModel;
import vst.science.ontologies.resoners.owl.JenaOWLReasonerAdapter;
import vst.science.ontologies.templates.CurrentRPDOnt;
import vst.science.ontologies.utils.write.ONT_WRITE_FORMAT;
import vst.science.ontologies.utils.write.OntologyWriter;

public class OWLReasonerMain {
    public static void main(String[] args) {
        OntModel model = CurrentRPDOnt.model;

        JenaOWLReasonerAdapter adapter = new JenaOWLReasonerAdapter();
        OntModel inferredModel = adapter.applyReasoner(model);

        OntologyWriter.write2Console(
                inferredModel,
                ONT_WRITE_FORMAT.RDF_XML_ABBREV
        );
        OntologyWriter.write2File(
                inferredModel,
                ONT_WRITE_FORMAT.RDF_XML_ABBREV,
                "ontology/JenaInferred.owx"
        );
    }
}
