package vst.science.ontologies.experiments;

import org.apache.jena.ontology.OntModel;
import vst.science.ontologies.templates.BaseOnt;
import vst.science.ontologies.utils.write.ONT_WRITE_FORMAT;
import vst.science.ontologies.utils.write.OntologyWriter;

/**
 * Базовый каркас онтологий
 */
public class BaseOntMain {
    public static void main(String[] args) {
        OntModel model = BaseOnt.model;

        OntologyWriter.write2File(
                model,
                ONT_WRITE_FORMAT.RDF_XML_ABBREV,
                "ontology/baseOntFromJena.owx"
        );

        OntologyWriter.write2Console(
                model,
                ONT_WRITE_FORMAT.RDF_XML_ABBREV
        );
    }
}
