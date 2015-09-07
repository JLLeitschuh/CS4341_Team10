//import csv
import java.util.ArrayList;
import java.io.FileReader;
import java.io.*;
import java.util.Scanner;

public class ParseFile {

    ArrayList gridWeights = new ArrayList();

    public ParseFile() throws FileNotFoundException {
        Scanner sc=new Scanner(new FileReader("src/board.txt"));
        while (sc.hasNextLine()){
            System.out.println(sc.next());
        }
    }


    public void readFile() {

        /*
        with open(fileToParse, 'r') as f:
        reader = csv.reader(f, delimiter="\t")
        d = list(reader)
        for inY,y in enumerate(d):
        for inX,x in enumerate(y):
        try:
        c = int(x)
                except ValueError:
        c = 1
        self.gridWeights.append( [inX, inY, c] )

        def printWeights(self):
        print(self.gridWeights)

        p = parseFile()
        p.readFile()
        p.printWeights()
        */
    }


}
