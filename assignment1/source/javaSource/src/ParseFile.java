//import csv

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParseFile {

    ArrayList <int[]> gridWeights = new ArrayList();

    public  SquareGrid getFromFile(String fileName) throws FileNotFoundException {
        int pointY = 0;
        int highestPointX = 0;
        List<Point> pointList = new ArrayList<Point>();
        // http://stackoverflow.com/questions/15183761/how-to-check-the-end-of-line-using-scanner
        InputStream stream1 = getClass().getResourceAsStream(fileName);
        //input.getResourceAsStream("myfile.txt");

        Scanner fileScanner = new Scanner(stream1);//new FileReader(fileName));
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            System.out.println("");

            int pointX = 0;
            Scanner lineScanner = new Scanner(line);
            while (lineScanner.hasNext()) {
                String token = lineScanner.next();
                System.out.print(token);
                Point newPoint;
                try {
                    newPoint = new Point(pointX, pointY, Integer.parseInt(token));
                }
                catch (NumberFormatException e) {
                    newPoint = new Point(pointX, pointY, token);
                }
                pointList.add(newPoint);
                pointX++;
            }
            highestPointX = pointX > highestPointX ? pointX : highestPointX;
            lineScanner.close();
            pointY++;
        }
        fileScanner.close();
        System.out.println();
        System.out.println("Generated Point List");
        for (Point point : pointList) {
            System.out.println("\t "+ point.toString());
        }
        System.out.println("Size: x:" + highestPointX + " y:" + pointY);
        SquareGrid returnGrid = new SquareGrid(highestPointX, pointY, pointList);

//        for(int i = 0; i < gridWeights.size(); i++) {
//            for(int q = 0; q < 3; q++) {
//                System.out.println(gridWeights.get(i)[q]);
//            }
//        }
        return returnGrid;
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
