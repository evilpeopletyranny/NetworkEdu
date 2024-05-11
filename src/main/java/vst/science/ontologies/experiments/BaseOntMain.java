package vst.science.ontologies.experiments;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import vst.science.ontologies.creation.OntologyBuilder;
import vst.science.ontologies.utils.ONT_WRITE_FORMAT;
import vst.science.ontologies.utils.OntologyWriter;

import java.util.List;

import static vst.science.ontologies.dictionaries.Base.*;


public class BaseOntMain {
    public static void main(String[] args) {
        OntModel model = new OntologyBuilder("http://vst/science/ontologies/networkedu", OntModelSpec.OWL_DL_MEM)
                .addClass(Discipline)
                .addLabel(Discipline, Pair.of("Дисциплина", "RU"))

                .addClass(Competence)
                .addLabel(Competence, Pair.of("Компетенция", "RU"))

                .addClass(Indicator)
                .addLabel(Indicator, Pair.of("Индикатор", "RU"))

                .addClass(Descriptor)
                .addLabel(Descriptor, Pair.of("Дескриптор", "RU"))

                .addClass(Knowledge, Descriptor)
                .addLabel(Knowledge, Pair.of("Знание", "RU"))

                .addClass(Ability, Descriptor)
                .addLabel(Ability, Pair.of("Умение", "RU"))

                .addClass(Skill, Descriptor)
                .addLabel(Skill, Pair.of("Навык", "RU"))

                .addCoveringTheorem(
                        Descriptor,
                        List.of(Knowledge, Ability, Skill)
                )

                .addObjectProperty(hasDescriptor)
                .addLabel(hasDescriptor, Pair.of("имеет_дескриптор", "RU"))
                .addRestrictions(hasDescriptor, Indicator, Descriptor)

                .addObjectProperty(hasIndicator)
                .addLabel(hasIndicator, Pair.of("имеет_индикатор", "RU"))
                .addRestrictions(hasIndicator, Competence, Indicator)

                .addObjectProperty(hasPrevDiscipline)
                .addLabel(hasPrevDiscipline, Pair.of("имеет_предыдущую_дисциплину", "RU"))
                .addRestrictions(hasPrevDiscipline, Discipline, Discipline)

                .addObjectProperty(hasNextDiscipline)
                .addLabel(hasNextDiscipline, Pair.of("имеет_следующую_дисциплину", "RU"))
                .addRestrictions(hasNextDiscipline, Discipline, Discipline)

                .addObjectProperty(hasInputCompetence)
                .addLabel(hasInputCompetence, Pair.of("имеет_входную_компетенцию", "RU"))
                .addRestrictions(hasInputCompetence, Discipline, Competence)

                .addObjectProperty(hasOutputCompetence)
                .addLabel(hasOutputCompetence, Pair.of("имеет_выходную_компетенцию", "RU"))
                .addRestrictions(hasOutputCompetence, Discipline, Competence)

                .build();

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
