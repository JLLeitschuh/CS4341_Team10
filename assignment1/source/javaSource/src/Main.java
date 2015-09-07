import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] Args) {

        new Main().printGraphLayout();
        try {
            new ParseFile();
        }
        catch (FileNotFoundException e) {

        }

    }

    public void printGraphLayout() {
        System.out.println("Graph Layout");
        System.out.println(" " + " x " + "\u2192");
        System.out.println("y");
        System.out.println("\u2193");
        System.out.println("End Graph Layout");
        System.out.println("");
    }
}
