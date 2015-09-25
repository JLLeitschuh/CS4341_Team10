package edu.wpi.cs4341.ga;

import java.util.*;

public class Algorithm {
    private static final int TOURNAMENT_SIZE = 5;
    private static final boolean ELITISM = true;
    private static final boolean CULLING = true;
    private static final int CULLING_PERCENT = 40;
    private static final double MUTATION_RATE = 0.015;
    private static final Random randomGenerator = new Random();
    private AbstractPuzzle puzzle;
    private AbstractIndividual bestIndividual = null;
    private int bestIndividualPopulationNumber;

    public Algorithm(AbstractPuzzle puzzle){
        this.puzzle = puzzle;
    }

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

    public AbstractIndividual getBestIndividual(){
        return this.bestIndividual;
    }

    public int getBestIndividualPopulationNumber(){
        return bestIndividualPopulationNumber;
    }

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




    private void mutate(AbstractIndividual individual){
        individual.mutate(puzzle);
    }

    private AbstractIndividual crossOver(AbstractIndividual individualA, AbstractIndividual individualB){
        return individualA.crossOver(individualB);
    }

    private AbstractIndividual tournamentSelection(List<AbstractIndividual> individuals){
        // Guarantees that the tournament will be between unique individuals
        Set<AbstractIndividual> tournamentIndividuals = new HashSet();
        assert individuals.size() > TOURNAMENT_SIZE : "Tournament size is smaller than the population passed";
        while(tournamentIndividuals.size() < TOURNAMENT_SIZE){
            tournamentIndividuals.add(individuals.get(randomGenerator.nextInt(individuals.size())));
        }
        return new Population(new ArrayList(tournamentIndividuals), Population.TEST_POPULATION).getBestIndividual();
    }

    private AbstractIndividual tournamentSelection(Population population){
        if(CULLING){
            return tournamentSelection(population.getIndividualRange(0, CULLING_PERCENT).get(0));
        } else {
            return tournamentSelection(population.getIndividuals());
        }
    }

}
