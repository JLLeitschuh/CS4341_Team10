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
    private AbstractIndividual bestInvididual;

    public Algorithm(AbstractPuzzle puzzle){
        this.puzzle = puzzle;
    }

    public Population evolvePopulation(Population population){
        List<AbstractIndividual> nextPopulation = new ArrayList<AbstractIndividual>();

        if(ELITISM){
            nextPopulation.add(population.getBestIndividual());
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




    public void mutate(AbstractIndividual individual){
        individual.mutate();
    }

    public AbstractIndividual crossOver(AbstractIndividual individualA, AbstractIndividual individualB){
        return individualA.crossOver(individualB);
    }

    public AbstractIndividual tournamentSelection(List<AbstractIndividual> individuals){
        List<AbstractIndividual> tournamentIndividuals = new ArrayList<>();
        for(int i = 0; i < TOURNAMENT_SIZE; i++ ){
            tournamentIndividuals.add(individuals.get(randomGenerator.nextInt(individuals.size())));
        }
        return new Population(tournamentIndividuals, Population.TEST_POPULATION).getBestIndividual();
    }

    public AbstractIndividual tournamentSelection(Population population){
        return tournamentSelection(population.getIndividuals());
    }

}
