package edu.wpi.cs4341;

import edu.wpi.cs4341.Puzzle2.Puzzle2;
import edu.wpi.cs4341.ga.AbstractPuzzle;
import edu.wpi.cs4341.ga.Algorithm;
import edu.wpi.cs4341.ga.ParseFile;
import edu.wpi.cs4341.ga.Population;
import edu.wpi.cs4341.puzzle1.Puzzle1;
import edu.wpi.cs4341.puzzle3.Puzzle3;

import java.util.List;

public class Main {
    private static int puzzleNumber = Integer.MIN_VALUE;
    private static String fileName;
    private static int secondRuntime = Integer.MIN_VALUE;

    public static void main(String[] args) {
        // Read arguments.
        int returnValue = parseCmdArgs(args);
        if ( returnValue == -1) {
            System.err.println("Arguments invalid. Terminating.");
            printHelp();
            System.exit(-1);
        } else if (returnValue == 1) {
            printHelp();
            System.exit(0);
        } else if (puzzleNumber == Integer.MIN_VALUE || fileName == null || secondRuntime == Integer.MIN_VALUE){
            System.err.println("Missing param");
            printHelp();
            System.exit(-1);
        }

        System.out.println("Using puzzle: " + puzzleNumber + "\nUsing filename: " + fileName);

        // Try to read from file, and run GA
        ParseFile parseFile = new ParseFile();
        List<String> fileLines = parseFile.getFromFile(fileName);
        AbstractPuzzle abstractPuzzle = getPuzzle(puzzleNumber, fileLines);
        Population currentPopulation = new Population(abstractPuzzle.getIndividuals(), 0);
        Algorithm algorithm = new Algorithm(abstractPuzzle);

        // Start timer for GA.
        long expectedEndTime = System.currentTimeMillis() + secondRuntime * 1000;
        long startTime = System.currentTimeMillis();
        // Run GA for x generation

        while (System.currentTimeMillis() < expectedEndTime) {
            currentPopulation = algorithm.evolvePopulation(currentPopulation);
            algorithm.storeIfBestIndividual(currentPopulation.getBestIndividual(), currentPopulation.getGenerationNumber());
        }

        long endTime = System.currentTimeMillis();
        System.out.println("elapsed milliseconds: " + (endTime - startTime));
        System.out.println("Populations generated: " + currentPopulation.getGenerationNumber());
        System.out.println("Best Gene's Fitness: " + algorithm.getBestIndividual().getFitness());
        System.out.println("Best Gene: " + algorithm.getBestIndividual());
        System.out.println("Best Gene from Population: " + algorithm.getBestIndividualPopulationNumber());
    }

    /**
     * Prints out the help message for the program.
     */
    private static void printHelp(){
        String helpText =
                "The program accepts the following args\n" +
                "\t--help              print this message\n" +
                "\t--puzzle [1-3]      Use this puzzle\n" +
                "\t--filename [file]   The file to read.\n" +
                "\t--seconds [integer] The number of seconds to run the program";
        System.out.println(helpText);
    }

    /**
     * Parses all of the input params and figures out what param to assign where.
     * @param args The args passed at runtime
     * @return Status, 0 means success, -1 means failure, 1 means help argument was provided.
     */
    private static int parseCmdArgs(String[] args) {
        if (args.length == 0) {
            return -1;
        }
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--help") || args.equals("/?")) {
                return 1;
            } else if (args[i].equals("--puzzle")) {
                if (args[i + 1].equals("1")) {
                    puzzleNumber = 1;
                } else if (args[i + 1].equals("2")) {
                    puzzleNumber = 2;
                } else if (args[i + 1].equals("3")) {
                    puzzleNumber = 3;
                } else {
                    System.err.println("Bad --puzzle value");
                    return -1;
                }
                i++;
            } else if (args[i].equals("--filename")) {
                fileName = args[i + 1];
                i++;
            } else if (args[i].equals("--seconds")) {
                try {
                    secondRuntime = Integer.valueOf(args[i + 1]);
                    i++;
                } catch (NumberFormatException e){
                    System.err.println("Bad --seconds value");
                    System.err.println("Must be an integer value");
                    e.printStackTrace();
                    return -1;
                }
            }
        }
        return 0;
    }

    /**
     * Gets the respective puzzle from the puzzle number.
     * @param num The puzzle number we want
     * @param inputStrings The input strings from the input file. Each List element should NOT contain a newline.
     * @return The puzzle for the given number.
     */
    public static AbstractPuzzle getPuzzle(int num, List<String> inputStrings){
        switch (num){
            case 1:
                return new Puzzle1(inputStrings);
            case 2:
                return new Puzzle2(inputStrings);
            case 3:
                return new Puzzle3(inputStrings);
            default:
                throw new Error("Invalid puzzle number. Number must be between 1 and 3");
        }
    }
}
