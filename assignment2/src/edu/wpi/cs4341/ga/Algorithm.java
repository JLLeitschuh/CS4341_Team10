package edu.wpi.cs4341.ga;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Algorithm {
    private static final int TOURNAMENT_SIZE = 5;
    private static final boolean ELITISM = true;
    private static final boolean CULLING = false;
    private static final Random randomGenerator = new Random();
    private AbstractPuzzle puzzle;
    private AbstractIndividual bestIndividual = null;
    private int bestIndividualPopulationNumber;

    public Algorithm(AbstractPuzzle puzzle){
        this.puzzle = puzzle;
    }

    public boolean storeIfBestIndividual(final AbstractIndividual bestIndividual, final int bestIndividualPopulationNumber){
        /* Only store if this individual if it is better, not if it is the same */
        if (this.bestIndividual == null || this.bestIndividual.getFitness() < bestIndividual.getFitness()){
            this.bestIndividual = bestIndividual;
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
            mutate(individual);
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
        List<AbstractIndividual> tournamentIndividuals = new ArrayList<>();
        for(int i = 0; i < TOURNAMENT_SIZE; i++ ){
            tournamentIndividuals.add(individuals.get(randomGenerator.nextInt(individuals.size())));
        }
        return new Population(tournamentIndividuals, Population.TEST_POPULATION).getBestIndividual();
    }

    private AbstractIndividual tournamentSelection(Population population){
        return tournamentSelection(population.getIndividuals());
    }

}
