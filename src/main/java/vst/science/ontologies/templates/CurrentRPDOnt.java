package vst.science.ontologies.templates;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import vst.science.ontologies.creation.OntologyBuilder;
import vst.science.ontologies.dictionaries.AOS;
import vst.science.ontologies.dictionaries.OTU;

import java.util.List;

import static vst.science.ontologies.dictionaries.Base.*;
import static vst.science.ontologies.dictionaries.Base.hasDescriptor;

public class CurrentRPDOnt {
    public static OntModel model = new OntologyBuilder("http://vst/science/ontologies/networkedu", OntModelSpec.OWL_DL_MEM)
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
            .addInverseFunctionProperty(hasPrevDiscipline, hasNextDiscipline)

            .addObjectProperty(hasInputCompetence)
            .addLabel(hasInputCompetence, Pair.of("имеет_входную_компетенцию", "RU"))
            .addRestrictions(hasInputCompetence, Discipline, Competence)

            .addObjectProperty(hasOutputCompetence)
            .addLabel(hasOutputCompetence, Pair.of("имеет_выходную_компетенцию", "RU"))
            .addRestrictions(hasOutputCompetence, Discipline, Competence)


            .addIndividual(Discipline, AOS.Discipline)
            .addLabel(AOS.Discipline, Pair.of("АОС в СУ", "RU"))

            .addIndividual(Competence, AOS.Competence)
            .addLabel(AOS.Competence, Pair.of("ОПК-2", "RU"))
            .addIndividualProperty(AOS.Discipline, hasOutputCompetence, AOS.Competence)

            .addIndividual(Indicator, AOS.Indicator1)
            .addLabel(AOS.Indicator1, Pair.of("ИОПК-2.1", "RU"))
            .addIndividualProperty(AOS.Competence, hasIndicator, AOS.Indicator1)

            .addIndividual(Indicator, AOS.Indicator2)
            .addLabel(AOS.Indicator2, Pair.of("ИОПК-2.2", "RU"))
            .addIndividualProperty(AOS.Competence, hasIndicator, AOS.Indicator2)

            .addIndividual(Knowledge, AOS.Knowledge1)
            .addLabel(AOS.Knowledge1, Pair.of("Методы цифровой обрабокти сигналов", "RU"))
            .addIndividualProperty(AOS.Indicator1, hasDescriptor, AOS.Knowledge1)

            .addIndividual(Knowledge, AOS.Knowledge2)
            .addLabel(AOS.Knowledge2, Pair.of("Системы паралельной обработки", "RU"))
            .addIndividualProperty(AOS.Indicator1, hasDescriptor, AOS.Knowledge2)

            .addIndividual(Knowledge, AOS.Knowledge3)
            .addLabel(AOS.Knowledge3, Pair.of("Представление многомерных сигналов", "RU"))
            .addIndividualProperty(AOS.Indicator2, hasDescriptor, AOS.Knowledge3)

            .addIndividual(Ability, AOS.Ability1)
            .addLabel(AOS.Ability1, Pair.of("Алгоритмы цифровой обработки сигналов в системах управления", "RU"))
            .addIndividualProperty(AOS.Indicator1, hasDescriptor, AOS.Ability1)
            .addIndividualProperty(AOS.Indicator2, hasDescriptor, AOS.Ability1)

            .addIndividual(Skill, AOS.Skill1)
            .addLabel(AOS.Skill1, Pair.of("Навыки по цифровой обработке сигналов", "RU"))
            .addIndividualProperty(AOS.Indicator1, hasDescriptor, AOS.Skill1)

            .addIndividual(Skill, AOS.Skill2)
            .addLabel(AOS.Skill2, Pair.of("Разработка алгоритмического и программного продукта анализа сигналов", "RU"))
            .addIndividualProperty(AOS.Indicator2, hasDescriptor, AOS.Skill2)


            .addIndividual(Discipline, OTU.Discipline)
            .addLabel(OTU.Discipline, Pair.of("ОТУ", "RU"))
            .addIndividualProperty(OTU.Discipline, hasNextDiscipline, AOS.Discipline)

            .addIndividual(Competence, OTU.Competence)
            .addLabel(OTU.Competence, Pair.of("ПКС-1", "RU"))
            .addIndividualProperty(OTU.Discipline, hasOutputCompetence, OTU.Competence)

            .addIndividual(Indicator, OTU.Indicator)
            .addLabel(OTU.Indicator, Pair.of("ИПКС-1.1", "RU"))
            .addIndividualProperty(OTU.Competence, hasIndicator, OTU.Indicator)

            .addIndividual(Knowledge, OTU.Knowledge1)
            .addLabel(OTU.Knowledge1, Pair.of("Основы моделирования и расчетов в области автоматики", "RU"))
            .addIndividualProperty(OTU.Indicator, hasDescriptor, OTU.Knowledge1)

            .addIndividual(Knowledge, OTU.Knowledge2)
            .addLabel(OTU.Knowledge2, Pair.of("Принципы автоматического управления", "RU"))
            .addIndividualProperty(OTU.Indicator, hasDescriptor, OTU.Knowledge2)

            .addIndividual(Knowledge, OTU.Knowledge3)
            .addLabel(OTU.Knowledge3, Pair.of("Звенья и схемы автоматических систем", "RU"))
            .addIndividualProperty(OTU.Indicator, hasDescriptor, OTU.Knowledge3)

            .addIndividual(Knowledge, OTU.Knowledge4)
            .addLabel(OTU.Knowledge4, Pair.of("Методы анализа устойчивости систем", "RU"))
            .addIndividualProperty(OTU.Indicator, hasDescriptor, OTU.Knowledge4)

            .addIndividual(Knowledge, OTU.Knowledge5)
            .addLabel(OTU.Knowledge5, Pair.of("Принципы управления техническими системами", "RU"))
            .addIndividualProperty(OTU.Indicator, hasDescriptor, OTU.Knowledge5)

            .addIndividual(Ability, OTU.Ability1)
            .addLabel(OTU.Ability1, Pair.of("Компьютерное моделирование систем управления", "RU"))
            .addIndividualProperty(OTU.Indicator, hasDescriptor, OTU.Ability1)

            .addIndividual(Ability, OTU.Ability2)
            .addLabel(OTU.Ability2, Pair.of("Проектирование систем автоматического управления", "RU"))
            .addIndividualProperty(OTU.Indicator, hasDescriptor, OTU.Ability2)

            .addIndividual(Ability, OTU.Ability3)
            .addLabel(OTU.Ability3, Pair.of("Составление математичсеких моделей объектов", "RU"))
            .addIndividualProperty(OTU.Indicator, hasDescriptor, OTU.Ability3)

            .addIndividual(Ability, OTU.Ability4)
            .addLabel(OTU.Ability4, Pair.of("Технические средства для систем регулирования", "RU"))
            .addIndividualProperty(OTU.Indicator, hasDescriptor, OTU.Ability4)

            .addIndividual(Skill, OTU.Skill1)
            .addLabel(OTU.Skill1, Pair.of("Методы моделирования процессов управления", "RU"))
            .addIndividualProperty(OTU.Indicator, hasDescriptor, OTU.Skill1)

            .addIndividual(Skill, OTU.Skill2)
            .addLabel(OTU.Skill2, Pair.of("Навыки работы с основными измерительными приборами", "RU"))
            .addIndividualProperty(OTU.Indicator, hasDescriptor, OTU.Skill2)

            .addIndividual(Skill, OTU.Skill3)
            .addLabel(OTU.Skill3, Pair.of("Стандарты в области автоматизации и метрологии", "RU"))
            .addIndividualProperty(OTU.Indicator, hasDescriptor, OTU.Skill3)

            .build();
}
