package edu.wpi.cs4341;

import edu.wpi.cs4341.ga.AbstractPuzzle;
import edu.wpi.cs4341.ga.Algorithm;
import edu.wpi.cs4341.ga.ParseFile;
import edu.wpi.cs4341.ga.Population;
import edu.wpi.cs4341.puzzle1.Puzzle1;
import edu.wpi.cs4341.puzzle3.Puzzle3;

import java.util.Date;
import java.util.List;

public class Main {
    private static int puzzleNumber;
    private static String fileName;

    public static void main(String[] args) {

        // Start timer for GA.
        long startTime = new Date().getTime();

        // Read arguments.
        /*
        int retval = parseCmdArgs(args);
        if (retval != 0) {
            System.out.println("No arguments given. Terminating.");
            System.exit(-1);
        }
        */

        // Hard coded for testing purposes
        puzzleNumber = 1;
        fileName = "puzzleOne.txt";


        System.out.println("Using puzzle: " + puzzleNumber + "\nUsing filename: " + fileName);

        // Try to read from file, and run GA
        ParseFile parseFile = new ParseFile();
        List<String> fileLines = parseFile.getFromFile("/" + fileName);
        AbstractPuzzle abstractPuzzle = getPuzzle(puzzleNumber, fileLines);
        Population currentPopulation = new Population(abstractPuzzle.getIndividuals(), 0);
        Algorithm algorithm = new Algorithm(abstractPuzzle);

        // Run GA for x generation
        for (int i = 0; i < 500; i++) {
            currentPopulation = algorithm.evolvePopulation(currentPopulation);
            algorithm.storeIfBestIndividual(currentPopulation.getBestIndividual(), currentPopulation.getGenerationNumber());
        }

        long endTime = new Date().getTime();
        System.out.println("elapsed milliseconds: " + (endTime - startTime));

        System.out.println("Best Gene's Fitness: " + algorithm.getBestIndividual().getFitness());
        System.out.println("Best Gene: " + algorithm.getBestIndividual());
        System.out.println("Best Gene from Population: " + algorithm.getBestIndividualPopulationNumber());
    }

    static int parseCmdArgs(String[] args) {
        if (args.length == 0) {
            return -1;
        }
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--help") || args.equals("/?")) {
                return -1;
            } else if (args[i].equals("--puzzle")) {
                if (args[i + 1].equals("1")) {
                    puzzleNumber = 1;
                } else if (args[i + 1].equals("2")) {
                    puzzleNumber = 2;
                } else if (args[i + 1].equals("3")) {
                    puzzleNumber = 3;
                } else {
                    System.out.println("Bad --puzzle value");
                    return -1;
                }
                i++;
            } else if (args[i].equals("--filename")) {
                fileName = args[i + 1];
                i++;
            }
        }
        return 0;
    }


    public static AbstractPuzzle getPuzzle(int num, List<String> inputStrings){
        switch (num){
            case 1:
                return new Puzzle1(inputStrings);
            case 3:
                return new Puzzle3(inputStrings);
            default:
                throw new Error("Invalid puzzle number. Number must be between 1 and 3");
        }
    }
}
