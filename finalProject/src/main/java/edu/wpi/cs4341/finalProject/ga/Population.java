package edu.wpi.cs4341.ga;

import java.util.ArrayList;
import java.util.List;

public class Population {
    public static final int TEST_POPULATION = Integer.MIN_VALUE;
    private final List<AbstractIndividual> abstractIndividuals;
    private final AbstractIndividual bestIndividual;
    private final int generationNumber;

    public Population(List<AbstractIndividual> abstractIndividuals, int generationNumber){
        this.generationNumber = generationNumber;
        //Sort in order of best individual first
        this.abstractIndividuals = new ArrayList<>(abstractIndividuals);
        this.abstractIndividuals.sort((m, n) -> Float.compare(n.getFitness(), m.getFitness()));
        this.bestIndividual = this.abstractIndividuals.get(0);
    }

    /**
     * Gets a set of individuals
     * @param initial The lower bound of the percentage of individuals to retrieve.
     * @param range What percentages of the population the genes should be pulled from.
     * @return A list of genes from each of the percentage ranges of the population.
     */
    public List<List<AbstractIndividual>> getIndividualRange (int initial, int ... range) {
        List<List<AbstractIndividual>> rangeList = new ArrayList<>();
        int startRange = initial;
        for (int endRange : range) {
            double startAsPercent = (double)startRange/100.0;
            double endAsPercent = (double)endRange/100.0;
            int sIndex = (int)(abstractIndividuals.size() * startAsPercent),
                eIndex = (int)(abstractIndividuals.size() * endAsPercent);
            rangeList.add(new ArrayList<AbstractIndividual>(abstractIndividuals.subList(sIndex, eIndex)));
            startRange = endRange;
        }
        return rangeList;
    }

    /**
     * @return Gets all of the individuals from this population.
     */
    public List<AbstractIndividual> getIndividuals() {
        return this.abstractIndividuals;
    }

    /**
     * @return The size of this population.
     */
    public int size(){
        return abstractIndividuals.size();
    }

    /**
     * Retrieves the best individual from this population.
     * @return The best abstract individual.
     */
    public AbstractIndividual getBestIndividual(){
        return bestIndividual;
    }

    /**
     * The generation that this population represents.
     * @return The generation number.
     */
    public int getGenerationNumber(){
        return this.generationNumber;
    }

    /**
     * Creates the next generation using this population.
     * @param individuals The individuals that should exist in the next generation.
     * @return The newly constructed population.
     */
    public Population nextGeneration(List<AbstractIndividual> individuals){
        assert abstractIndividuals.size() == individuals.size() : "The size of the population is different from the expected.";
        return new Population(individuals, generationNumber+1);
    }
}
