package vst.science.ontologies.utils;

import org.apache.jena.ontology.OntModel;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static vst.science.ontologies.utils.ONT_WRITE_FORMAT.*;

final public class OntologyWriter {
    public static void write2File(OntModel model, ONT_WRITE_FORMAT format, String path) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(path))) {
            model.write(writer, format.value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void write2Console(OntModel model, ONT_WRITE_FORMAT format) {
        model.write(System.out, format.value);
    }
}
