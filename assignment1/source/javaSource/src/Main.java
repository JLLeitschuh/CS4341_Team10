import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] Args) {

        printGraphLayout();
        try {
            ParseFile n = new ParseFile();
            SquareGrid squareGrid = n.getFromFile("/board.txt");
            ArrayList <Neighbor> neighborPath = AStarSearch.runAStarSearch(squareGrid);
            //AStarSearch2.runAStarSearch(squareGrid);
            DrawGrid dg = new DrawGrid(squareGrid, neighborPath);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

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
