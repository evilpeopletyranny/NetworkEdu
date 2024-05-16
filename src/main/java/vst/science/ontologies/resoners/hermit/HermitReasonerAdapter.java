package vst.science.ontologies.resoners.hermit;

import org.apache.jena.ontology.OntModel;
import org.semanticweb.HermiT.ReasonerFactory;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.util.*;
import vst.science.ontologies.resoners.IReasonerAdapter;
import vst.science.ontologies.resoners.hermit.axioms.InferredObjectPropertyDomainAxiomGenerator;
import vst.science.ontologies.resoners.hermit.axioms.InferredObjectPropertyRangeAxiomGenerator;
import vst.science.ontologies.utils.mappers.IOntMapper;
import vst.science.ontologies.utils.mappers.Ont2StreamMapper;

import java.util.ArrayList;
import java.util.List;

public class HermitReasonerAdapter implements IReasonerAdapter {
    protected IOntMapper mapper = new Ont2StreamMapper();

    public HermitReasonerAdapter() {
    }

    @Override
    public OntModel applyReasoner(OntModel ontModel) throws Exception {
        OWLOntology owlOntology = mapper.ontModel2OWLontology(ontModel);

        OWLReasonerFactory reasonerFactory = new ReasonerFactory();
        OWLReasoner reasoner = reasonerFactory.createReasoner(owlOntology);

        // Применяем инференции и закрываем reasoner
        reasoner.precomputeInferences();

        // Генерация выведенных аксиом
        List<InferredAxiomGenerator<? extends OWLAxiom>> generators = new ArrayList<>();
        generators.add(new InferredSubClassAxiomGenerator());
        generators.add(new InferredClassAssertionAxiomGenerator());
        generators.add(new InferredObjectPropertyCharacteristicAxiomGenerator());
        generators.add(new InferredInverseObjectPropertiesAxiomGenerator());
        generators.add(new InferredObjectPropertyDomainAxiomGenerator());
        generators.add(new InferredObjectPropertyRangeAxiomGenerator());

        OWLOntologyManager manager = owlOntology.getOWLOntologyManager();

        OWLOntology inferredOntology = manager.createOntology();
        InferredOntologyGenerator iog = new InferredOntologyGenerator(reasoner, generators);
        iog.fillOntology(manager.getOWLDataFactory(), inferredOntology);

        manager.addAxioms(owlOntology, inferredOntology.axioms());

        reasoner.dispose();
        return mapper.owlOntology2OntModel(owlOntology);
    }
}