package vst.science.ontologies.utils;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import vst.science.ontologies.creation.OntologyBuilder;
import vst.science.ontologies.templates.BaseFrameworkBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static vst.science.ontologies.dictionaries.Base.*;
import static vst.science.ontologies.dictionaries.Base.Competence;

public class EduOntGenerator {
    private final ThreadLocalRandom random = ThreadLocalRandom.current();
    private final OntologyBuilder builder;
    private final String ns;

    private final List<Individual> disciplines;
    private final List<Individual> competences;
    private final List<Individual> indicators;
    private final List<Individual> kasList;


    public EduOntGenerator() {
        this.builder = BaseFrameworkBuilder.builder;
        this.ns = builder.getNs();

        this.disciplines = new ArrayList<>();
        this.competences = new ArrayList<>();
        this.indicators = new ArrayList<>();
        this.kasList = new ArrayList<>();
    }

    private Integer getRandomNumberInRange(int min, int max) {
        return random.nextInt(min, max);
    }


    public EduOntGenerator generateDisciplines(Integer min, Integer max) {
        for (int i = 1; i <= getRandomNumberInRange(min, max); i++)
            disciplines.add(builder.getClass(Discipline).createIndividual(ns + Discipline + i));
        return this;
    }

    public EduOntGenerator generateCompetences(Integer min, Integer max) {
        for (int i = 1; i <= getRandomNumberInRange(min, max); i++)
            competences.add(builder.getClass(Competence).createIndividual(ns + Competence + i));
        return this;
    }

    public EduOntGenerator generateHasOutputCompetenceProperties() {
        competences.forEach(competence -> disciplines.get(random.nextInt(disciplines.size()))
                .addProperty(builder.getOntProperty(hasOutputCompetence), competence));
        return this;
    }

    public EduOntGenerator generateHasInputCompetenceProperties() {
        competences.forEach(competence -> disciplines.get(random.nextInt(disciplines.size()))
                .addProperty(builder.getOntProperty(hasInputCompetence), competence));
        return this;
    }

    public EduOntGenerator generateIndicators(Integer min, Integer max) {
        for (int i = 1; i <= getRandomNumberInRange(min, max); i++)
            indicators.add(builder.getClass(Indicator).createIndividual(ns + Indicator + i));
        return this;
    }

    public EduOntGenerator generateHasIndicatorProperties() {
        indicators.forEach(indicator -> competences.get(random.nextInt(competences.size()))
                .addProperty(builder.getOntProperty(hasIndicator), indicator));
        return this;
    }

    public EduOntGenerator generateKAS(Integer min, Integer max) {
        List<OntClass> kas = List.of(
                builder.getClass(Knowledge),
                builder.getClass(Ability),
                builder.getClass(Skill)
        );
        for (int i = 0; i < getRandomNumberInRange(min, max); i++)
            kasList.add(kas.get(random.nextInt(kas.size())).createIndividual(ns + Descriptor + i));
        return this;
    }

    public EduOntGenerator generateHasDescriptorProperties() {
        kasList.forEach(kas -> indicators.get(random.nextInt(indicators.size()))
                .addProperty(builder.getOntProperty(hasDescriptor), kas));
        return this;
    }

    public OntModel getModel() {
        return builder.build();
    }
}
