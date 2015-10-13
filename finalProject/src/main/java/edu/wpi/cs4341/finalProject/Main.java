package edu.wpi.cs4341.finalProject;

import edu.wpi.cs4341.finalProject.ga.Algorithm;
import edu.wpi.cs4341.finalProject.ga.Population;
import edu.wpi.cs4341.finalProject.learningAI.ReversiPuzzle;

public class Main {

    public static final void main(String ...args){
        ReversiPuzzle puzzle = new ReversiPuzzle();
        Population currentPopulation = new Population(puzzle.getIndividuals(), 0);
        Algorithm algorithm = new Algorithm(puzzle);

        System.out.println("\n*** Initial Population Generated! ***\n");

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 2000; i++) {
            currentPopulation = algorithm.evolvePopulation(currentPopulation);
            algorithm.storeIfBestIndividual(currentPopulation.getBestIndividual(), currentPopulation.getGenerationNumber());
            System.out.println("Generation Number: " + i);
            //System.out.println("Best Gene from population: " + currentPopulation.getBestIndividual());
        }
        long endTime = System.currentTimeMillis();
        System.out.println("elapsed milliseconds: " + (endTime - startTime));
        System.out.println("Populations generated: " + currentPopulation.getGenerationNumber());
        System.out.println("Best Gene's Fitness: " + algorithm.getBestIndividual().getFitness());
        System.out.println("Best Gene: " + algorithm.getBestIndividual());
        System.out.println("Best Gene from Population: " + algorithm.getBestIndividualPopulationNumber());
    }
}
