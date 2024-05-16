package vst.science.ontologies.resoners.hermit.axioms;

import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.util.InferredAxiomGenerator;

import java.util.HashSet;
import java.util.Set;

public class InferredObjectPropertyDomainAxiomGenerator implements InferredAxiomGenerator<OWLPropertyDomainAxiom<?>> {

    protected void addAxioms(OWLObjectProperty entity, OWLReasoner reasoner, OWLDataFactory dataFactory, Set<OWLPropertyDomainAxiom<?>> result) {
        Set<OWLClass> domains = reasoner.getObjectPropertyDomains(entity, true).getFlattened();
        for (OWLClass domain : domains) {
            result.add(dataFactory.getOWLObjectPropertyDomainAxiom(entity, domain));
        }
    }

    @Override
    public String getLabel() {
        return "Inferred Object Property Domains";
    }

    @Override
    public Set<OWLPropertyDomainAxiom<?>> createAxioms(OWLDataFactory dataFactory, OWLReasoner reasoner) {
        Set<OWLPropertyDomainAxiom<?>> result = new HashSet<>();
        for (OWLObjectProperty property : reasoner.getRootOntology().getObjectPropertiesInSignature()) {
            addAxioms(property, reasoner, dataFactory, result);
        }
        return result;
    }
}
