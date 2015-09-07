//import csv
import java.util.ArrayList;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ParseFile {

    ArrayList gridWeights = new ArrayList();

    public ParseFile() {
    }


    public void readFile() {
        String fileToParse = "board.txt";

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
    }


}
