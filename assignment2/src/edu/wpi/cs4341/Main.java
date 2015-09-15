package edu.wpi.cs4341;

import edu.wpi.cs4341.ga.ParseFile;

import java.io.FileNotFoundException;
import java.util.Date;

public class Main {
    static String file_name = "";
    static int puzzle;

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
        puzzle = 1;
        file_name = "puzzleOne.txt";

        System.out.println("Using puzzle: " + puzzle + "\nUsing filename: " + file_name);

        // Try to read from file, and run GA
        try {
            ParseFile n = new ParseFile();
            n.getFromFile("/" + file_name);

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        long endTime = new Date().getTime();
        System.out.println("elapsed milliseconds: " + (endTime - startTime));



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
                    puzzle = 1;
                } else if (args[i + 1].equals("2")) {
                    puzzle = 2;
                } else if (args[i + 1].equals("3")) {
                    puzzle = 3;
                } else {
                    System.out.println("Bad --puzzle value");
                    return -1;
                }
                i++;
            } else if (args[i].equals("--filename")) {
                file_name = args[i + 1];
                i++;
            }
        }
        return 0;
    }

    /*
    public static IHeuristic setHeuristic(int num){
        IHeuristic returnVal= new Heuristic1(); //Department of Redundancy Department.
        switch (num){
            case 1:
                returnVal= new Heuristic1();
                break;
            case 2:
                returnVal= new Heuristic2();
                break;
            case 3:
                returnVal= new Heuristic3();
                break;
            case 4:
                returnVal= new Heuristic4();
                break;
            case 5:
                returnVal= new Heuristic5();
                break;
            case 6:
                returnVal= new Heuristic6();
                break;
            default:
                throw new Error("Heuristic must be a valid number from 1 to 6!");
        }
        return returnVal;
    }
    */
}
