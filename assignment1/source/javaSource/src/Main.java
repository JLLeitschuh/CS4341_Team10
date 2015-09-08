import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] Args) {

        printGraphLayout();
        try {
            ParseFile n = new ParseFile();
            SquareGrid squareGrid = n.getFromFile("/board.txt");
            AStarSearch.runAStarSearch(squareGrid);
            //AStarSearch2.runAStarSearch(squareGrid);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void printGraphLayout() {
        System.out.println("Graph Layout");
        System.out.println(" " + " x " + "\u2192");
        System.out.println("y");
        System.out.println("\u2193");
        System.out.println("End Graph Layout");
        System.out.println("");
    }
}
