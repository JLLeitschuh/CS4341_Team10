import java.io.FileNotFoundException;
import java.util.List;

public class Main {

    static String file_name = "";
    static int heuristic = 1;

    public static void main(String[] args) {
        int retval = parseCmdArgs(args);
        if (retval != 0) {
            System.out.println("No arguments given. Terminating.");
            System.exit(-1);
        }

        System.out.println("Using heuristic: " + heuristic + "\nUsing filename: " + file_name);


        printGraphLayout();
        try {
            ParseFile n = new ParseFile();
            SquareGrid squareGrid = n.getFromFile("/"+file_name);
            List<Neighbor> neighborPath = AStarSearch.runAStarSearch(squareGrid);
            //AStarSearch2.runAStarSearch(squareGrid);
            DrawGrid dg = new DrawGrid(squareGrid, neighborPath);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

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
}
