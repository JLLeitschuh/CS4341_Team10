package edu.wpi.cs4341.ga;

import java.util.List;

public class Population {
    private List<AbstractIndividual> abstractIndividuals;
    private AbstractIndividual bestIndividual;
    private int generationNumber;

    public Population(List<AbstractIndividual> abstractIndividuals, int generationNumber){
        this.abstractIndividuals = abstractIndividuals;
        this.generationNumber = generationNumber;
    }

    public List<AbstractIndividual> getIndividuals() {
        return this.abstractIndividuals;
    }

    public AbstractIndividual getBestIndividual(){
        return bestIndividual;
    }
}
