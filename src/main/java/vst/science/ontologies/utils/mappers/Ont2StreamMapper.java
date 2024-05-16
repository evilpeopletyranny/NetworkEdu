package vst.science.ontologies.utils.mappers;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import vst.science.ontologies.utils.write.ONT_WRITE_FORMAT;

import java.io.*;

public class Ont2StreamMapper implements IOntMapper {
    private ByteArrayOutputStream ontModel2Stream(OntModel ontModel) {
        var stream = new ByteArrayOutputStream();
        try (var bufferedStream = new BufferedOutputStream(stream)) {
            ontModel.write(bufferedStream, ONT_WRITE_FORMAT.RDF_XML.value);
            bufferedStream.flush();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stream;
    }

    private ByteArrayOutputStream owlOntology2Stream(OWLOntology ontology) {
        var stream = new ByteArrayOutputStream();
        try (var bufferedStream = new BufferedOutputStream(stream)) {
            OWLOntologyManager manager = ontology.getOWLOntologyManager();
            manager.saveOntology(ontology, bufferedStream);
            bufferedStream.flush();
        }
        catch (IOException | OWLOntologyStorageException e) {
            throw new RuntimeException(e);
        }
        return stream;
    }

    private OWLOntology stream2OWLOntology(ByteArrayOutputStream outputStream) {
        OWLOntology ontology;
        try(var stream = new BufferedInputStream(new ByteArrayInputStream(outputStream.toByteArray()))) {
            OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
            ontology = manager.loadOntologyFromOntologyDocument(stream);
        }
        catch (IOException | OWLOntologyCreationException e) {
            throw new RuntimeException(e);
        }
        return ontology;
    }

    private OntModel stream2OntModel(ByteArrayOutputStream outputStream) {
        OntModel model;
        try(var stream = new BufferedInputStream(new ByteArrayInputStream(outputStream.toByteArray()))) {
            model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
            model.read(stream, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return model;
    }

    @Override
    public OWLOntology ontModel2OWLontology(OntModel ontModel) {
        ByteArrayOutputStream output = ontModel2Stream(ontModel);
        return stream2OWLOntology(output);
    }

    @Override
    public OntModel owlOntology2OntModel(OWLOntology ontology) {
        ByteArrayOutputStream output = owlOntology2Stream(ontology);
        return stream2OntModel(output);
    }
}
