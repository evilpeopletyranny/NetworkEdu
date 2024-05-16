package vst.science.ontologies.experiments;

import org.apache.jena.ontology.OntModel;
import vst.science.ontologies.templates.DesiredRPDOnt;
import vst.science.ontologies.utils.write.ONT_WRITE_FORMAT;
import vst.science.ontologies.utils.write.OntologyWriter;

/**
 * АОС в СУ и ОТУ
 * Желаемая ситуация, АОС в СУ указана входная компетенция ПКС-1 - выходная от ОТУ.
 */
public class DesiredRPDOntMain {
    public static void main(String[] args) {
        OntModel model = DesiredRPDOnt.model;

        OntologyWriter.write2File(
                model,
                ONT_WRITE_FORMAT.RDF_XML_ABBREV,
                "ontology/desiredRPDSituationFromJena.owx"
        );

        OntologyWriter.write2Console(
                model,
                ONT_WRITE_FORMAT.RDF_XML_ABBREV
        );
    }
}
