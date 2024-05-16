package vst.science.ontologies.resoners.hermit.axioms;

import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.util.InferredAxiomGenerator;

import java.util.HashSet;
import java.util.Set;

public class InferredObjectPropertyRangeAxiomGenerator implements InferredAxiomGenerator<OWLPropertyRangeAxiom<?, ?>> {

    protected void addAxioms(OWLObjectProperty entity, OWLReasoner reasoner, OWLDataFactory dataFactory, Set<OWLPropertyRangeAxiom<?, ?>> result) {
        Set<OWLClass> ranges = reasoner.getObjectPropertyRanges(entity, true).getFlattened();
        for (OWLClass range : ranges) {
            result.add(dataFactory.getOWLObjectPropertyRangeAxiom(entity, range));
        }
    }

    @Override
    public String getLabel() {
        return "Inferred Object Property Ranges";
    }

    @Override
    public Set<OWLPropertyRangeAxiom<?, ?>> createAxioms(OWLDataFactory dataFactory, OWLReasoner reasoner) {
        Set<OWLPropertyRangeAxiom<?, ?>> result = new HashSet<>();
        for (OWLObjectProperty property : reasoner.getRootOntology().getObjectPropertiesInSignature()) {
            addAxioms(property, reasoner, dataFactory, result);
        }
        return result;
    }
}
