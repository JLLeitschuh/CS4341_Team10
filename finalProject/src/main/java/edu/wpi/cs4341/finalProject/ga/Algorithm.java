package edu.wpi.cs4341.finalProject.ga;

import java.util.*;

public class Algorithm {
    private static final int TOURNAMENT_SIZE = 2;
    private static final boolean ELITISM = true;
    private static final boolean CULLING = false;
    private static final int CULLING_PERCENT = 40;
    private static final double MUTATION_RATE = 0.015;
    private static final Random randomGenerator = new Random();
    private AbstractPuzzle puzzle;
    private AbstractIndividual bestIndividual = null;
    private int bestIndividualPopulationNumber;

    public Algorithm(AbstractPuzzle puzzle){
        this.puzzle = puzzle;
    }

    /**
     * Stores the given individual in the algorithm if it is the best.
     * @param nextBestIndividual The individual to compare with
     * @param bestIndividualPopulationNumber The population that this individual came from.
     * @return true if this individual was stored
     */
    public boolean storeIfBestIndividual(final AbstractIndividual nextBestIndividual, final int bestIndividualPopulationNumber){
        /* Only store if this individual if it is better, not if it is the same */
        //System.out.println((this.bestIndividual == null ? "null" : this.bestIndividual.getFitness()) + ", " + nextBestIndividual.getFitness());
        if (this.bestIndividual == null || this.bestIndividual.getFitness() < nextBestIndividual.getFitness()){
            this.bestIndividual = nextBestIndividual;
            this.bestIndividualPopulationNumber = bestIndividualPopulationNumber;
            return true;
        }
        return false;
    }

    /**
     * Provides access to the best individual that we have so far.
     * @return The current best individual.
     */
    public AbstractIndividual getBestIndividual(){
        return this.bestIndividual;
    }

    /**
     * Provides the access to the best population number
     * @return The population that the current best individual came from.
     */
    public int getBestIndividualPopulationNumber(){
        return bestIndividualPopulationNumber;
    }

    /**
     * Evolves the population to the next generation.
     * @param population The pollution to evolve
     * @return The new population
     */
    public Population evolvePopulation(Population population){
        List<AbstractIndividual> nextPopulation = new ArrayList<AbstractIndividual>();

        if(ELITISM){
            // We copy because we don't want to modify the previous generations individual.
            nextPopulation.add(population.getBestIndividual().copy());
        }

        assert nextPopulation.size() <= population.size() : "Next population started too big";
        while (nextPopulation.size() != population.size()) {
            AbstractIndividual individual1 = tournamentSelection(population);
            AbstractIndividual individual2 = tournamentSelection(population);
            AbstractIndividual newIndividual = crossOver(individual1, individual2);
            nextPopulation.add(newIndividual);
        }



        for(AbstractIndividual individual : nextPopulation){
            if (Math.random() <= MUTATION_RATE) {
                mutate(individual);
            }
        }
        return population.nextGeneration(nextPopulation);
    }

    /**
     * Mutates the given individual
     * @param individual The individual to mutate.
     */
    private void mutate(AbstractIndividual individual){
        individual.mutate(puzzle);
    }

    /**
     * Crosses this individual over another individual.
     * @param individualA The individual to use as the base.
     * @param individualB The individual to cross over with.
     * @return The new individual that results from the crossover of the two individuals.
     */
    private AbstractIndividual crossOver(AbstractIndividual individualA, AbstractIndividual individualB){
        return individualA.crossOver(individualB);
    }

    /**
     * Runs a selection process on a list of individuals.
     * @param individuals The individuals to pull from for the tournament.
     * @return The winning gene.
     */
    private AbstractIndividual tournamentSelection(List<AbstractIndividual> individuals){
        // Guarantees that the tournament will be between unique individuals
        Set<AbstractIndividual> tournamentIndividuals = new HashSet();
        assert individuals.size() > TOURNAMENT_SIZE : "Tournament size is smaller than the population passed";
        while(tournamentIndividuals.size() < TOURNAMENT_SIZE){
            tournamentIndividuals.add(individuals.get(randomGenerator.nextInt(individuals.size())));
        }
        return new Population(new ArrayList(tournamentIndividuals), Population.TEST_POPULATION).getBestIndividual();
    }

    /**
     * Runs a tournament on a population. If culling is enabled then it only gets the individuals
     * up to the {@link Algorithm#CULLING_PERCENT}
     * @param population The population to run the tournament on.
     * @return The winning individual.
     */
    private AbstractIndividual tournamentSelection(Population population){
        if(CULLING){
            return tournamentSelection(population.getIndividualRange(0, CULLING_PERCENT).get(0));
        } else {
            return tournamentSelection(population.getIndividuals());
        }
    }

}
