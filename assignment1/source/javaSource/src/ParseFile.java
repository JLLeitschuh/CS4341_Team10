//import csv
import java.util.ArrayList;
import java.io.FileReader;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class ParseFile {

    ArrayList <int[]> gridWeights = new ArrayList();

    public ParseFile() throws FileNotFoundException {
        int pointX = 0;
        int pointY = 0;
        // http://stackoverflow.com/questions/15183761/how-to-check-the-end-of-line-using-scanner
        Scanner fileScanner = new Scanner(new FileReader("src/board.txt"));
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            System.out.println("");


            Scanner lineScanner = new Scanner(line);
            while (lineScanner.hasNext()) {
                String token = lineScanner.next();
                System.out.print(token);
                int[] point = new int[3];
                try {
                    point[0] = pointX;
                    point[1] =pointY;
                    point[2] =Integer.parseInt(token);

                }
                catch (NumberFormatException e) {
                    point[0] = pointX;
                    point[1] =pointY;
                    point[2] =1;
                }
                gridWeights.add(point);
                pointY++;
            }
            lineScanner.close();
            pointX++;
        }
        fileScanner.close();

        for(int i = 0; i < gridWeights.size(); i++) {
            for(int q = 0; q < 3; q++) {
                System.out.println(gridWeights.get(i)[q]);
            }
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
