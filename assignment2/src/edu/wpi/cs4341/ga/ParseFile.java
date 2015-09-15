package edu.wpi.cs4341.ga;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParseFile {

    List <int[]> gridWeights = new ArrayList<>();

    public  void getFromFile(String fileName) throws FileNotFoundException {
        int pointY = 0;
        int highestPointX = 0;
        System.out.println(fileName);

        InputStream stream1 = getClass().getResourceAsStream(fileName);

        Scanner fileScanner = new Scanner(stream1);
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            System.out.println("");

            int pointX = 0;
            Scanner lineScanner = new Scanner(line);
            while (lineScanner.hasNext()) {
                String token = lineScanner.next();
                System.out.print(token);

                try {

                }
                catch (NumberFormatException e) {

                }
                //pointList.add();
                pointX++;
            }
            highestPointX = pointX > highestPointX ? pointX : highestPointX;
            lineScanner.close();
            pointY++;
        }
        fileScanner.close();
        System.out.println();
        System.out.println("Generated Point List");

        System.out.println("Size: x:" + highestPointX + " y:" + pointY);

        //return null;
    }


    public void readFile() {

    }


}
