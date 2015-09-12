import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;

public class Main {
    static String file_name = "";
    static int heuristic;

    public static void main(String[] args) {
        long startTime = new Date().getTime();
        int retval = parseCmdArgs(args);
        if (retval != 0) {
            System.out.println("No arguments given. Terminating.");
            System.exit(-1);
        }

        System.out.println("Using heuristic: " + heuristic + "\nUsing filename: " + file_name);


        printGraphLayout();
        try {
            ParseFile n = new ParseFile();
            SquareGrid squareGrid = n.getFromFile("/" + file_name);
            Neighbor goalNeightbor = AStarSearch.runAStarSearch(squareGrid, setHeuristic(heuristic));
            //AStarSearch2.runAStarSearch(squareGrid);
            DrawGrid dg = new DrawGrid(goalNeightbor);
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
            } else if (args[i].equals("--heuristic")) {
                if (args[i + 1].equals("1")) {
                    heuristic = 1;
                } else if (args[i + 1].equals("2")) {
                    heuristic = 2;
                } else if (args[i + 1].equals("3")) {
                    heuristic = 3;
                } else if (args[i + 1].equals("4")) {
                    heuristic = 4;
                } else if (args[i + 1].equals("5")) {
                    heuristic = 5;
                } else if (args[i + 1].equals("6")) {
                    heuristic = 6;
                } else {
                    System.out.println("Bad --heuristic value");
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

    public static void printGraphLayout() {
        System.out.println("Graph Layout");
        System.out.println(" " + " x " + "\u2192");
        System.out.println("y");
        System.out.println("\u2191");
        System.out.println("End Graph Layout");
        System.out.println("");
    }

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

}
