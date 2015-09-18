package edu.wpi.cs4341.ga;

import java.util.List;

public class Population {
    private List<AbstractIndividual> abstractIndividuals;
    private AbstractIndividual bestIndividual;
    private int generationNumber;

    public Population(List<AbstractIndividual> abstractIndividuals, int generationNumber){
        this.abstractIndividuals = abstractIndividuals;
        this.generationNumber = generationNumber;
        for(AbstractIndividual individual : this.abstractIndividuals){
            if(bestIndividual == null){
                bestIndividual = individual;
            } else if (bestIndividual.getFitness() < individual.getFitness()){
                this.bestIndividual = individual;
            }
        }

    }

    public List<AbstractIndividual> getIndividuals() {
        return this.abstractIndividuals;
    }

    public AbstractIndividual getBestIndividual(){
        return bestIndividual;
    }
}
