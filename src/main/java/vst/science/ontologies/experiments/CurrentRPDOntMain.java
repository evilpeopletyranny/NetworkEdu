package vst.science.ontologies.experiments;

import org.apache.jena.ontology.OntModel;
import vst.science.ontologies.templates.CurrentRPDOnt;
import vst.science.ontologies.utils.write.ONT_WRITE_FORMAT;
import vst.science.ontologies.utils.write.OntologyWriter;

/**
 * АОС в СУ и ОТУ
 */
public class CurrentRPDOntMain {
    public static void main(String[] args) {
        OntModel model = CurrentRPDOnt.model;

        OntologyWriter.write2File(
                model,
                ONT_WRITE_FORMAT.RDF_XML_ABBREV,
                "ontology/currentRPDSituationFromJena.owx"
        );

        OntologyWriter.write2Console(
                model,
                ONT_WRITE_FORMAT.RDF_XML_ABBREV
        );
    }
}
