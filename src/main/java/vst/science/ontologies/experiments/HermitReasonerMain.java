package vst.science.ontologies.experiments;

import org.apache.jena.ontology.OntModel;
import vst.science.ontologies.resoners.hermit.HermitReasonerAdapter;
import vst.science.ontologies.templates.CurrentRPDOnt;
import vst.science.ontologies.utils.write.ONT_WRITE_FORMAT;
import vst.science.ontologies.utils.write.OntologyWriter;

public class HermitReasonerMain {
    public static void main(String[] args) throws Exception {
        OntModel model = CurrentRPDOnt.model;

        HermitReasonerAdapter adapter = new HermitReasonerAdapter();
        OntModel inferredModel = adapter.applyReasoner(model);

        OntologyWriter.write2Console(
                inferredModel,
                ONT_WRITE_FORMAT.RDF_XML_ABBREV
        );
        OntologyWriter.write2File(
                inferredModel,
                ONT_WRITE_FORMAT.RDF_XML_ABBREV,
                "ontology/hermitInferred.owx"
        );
    }
}
