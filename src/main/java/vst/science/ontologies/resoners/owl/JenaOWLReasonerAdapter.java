package vst.science.ontologies.resoners.owl;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.*;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ReasonerRegistry;
import org.apache.jena.reasoner.ValidityReport;
import vst.science.ontologies.resoners.IReasonerAdapter;
import vst.science.ontologies.utils.modifiers.OntModifier;

public class JenaOWLReasonerAdapter implements IReasonerAdapter {
    private Reasoner reasoner;

    public JenaOWLReasonerAdapter() {
        reasoner = ReasonerRegistry.getOWLReasoner();
    }

    @Override
    public OntModel applyReasoner(OntModel ontModel) {
        InfModel infModel = createInfModel(ontModel);
        validateModel(infModel);
        OntModifier.addInferredInverseProperties(ontModel, infModel);
        return ontModel;
    }

    private InfModel createInfModel(OntModel model) {
        reasoner = reasoner.bindSchema(model);
        return ModelFactory.createInfModel(reasoner, model);
    }

    private void validateModel(InfModel model) {
        ValidityReport validity = model.validate();
        if (!validity.isValid()) {
            System.err.println("Ontology is inconsistent");
            validity.getReports().forEachRemaining(System.err::println);
        }
    }
}
