package vst.science.ontologies.experiments;

import org.apache.jena.ontology.OntModel;
import vst.science.ontologies.utils.EduOntGenerator;
import vst.science.ontologies.utils.write.ONT_WRITE_FORMAT;
import vst.science.ontologies.utils.write.OntologyWriter;

public class GenerateOntMain {
    public static void main(String[] args) {
        OntModel model = new EduOntGenerator()
                .generateDisciplines(5, 10)
                .generateCompetences(30, 60)
                .generateIndicators(180, 360)
                .generateKAS(900, 1800)
                .generateHasInputCompetenceProperties()
                .generateHasOutputCompetenceProperties()
                .generateHasIndicatorProperties()
                .generateHasDescriptorProperties()
                .getModel();

        OntologyWriter.write2File(
                model,
                ONT_WRITE_FORMAT.RDF_XML_ABBREV,
                "ontology/generatedOnt.owx"
        );

        OntologyWriter.write2Console(
                model,
                ONT_WRITE_FORMAT.RDF_XML_ABBREV
        );
    }
}
