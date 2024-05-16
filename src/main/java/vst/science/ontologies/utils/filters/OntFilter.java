package vst.science.ontologies.utils.filters;

import org.apache.jena.ontology.ObjectProperty;
import org.apache.jena.ontology.OntModel;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class OntFilter {
    public static List<ObjectProperty> getFilteredProperty(OntModel ontModel,
                                                           Predicate<ObjectProperty> predicate,
                                                           Function<ObjectProperty, ObjectProperty> function) {
        return ontModel.listObjectProperties()
                .filterKeep(predicate)
                .mapWith(function)
                .toList();
    }

    public static List<ObjectProperty> getFilteredProperty(OntModel ontModel,
                                                           Predicate<ObjectProperty> predicate) {
        return ontModel.listObjectProperties()
                .filterKeep(predicate)
                .toList();
    }
}
