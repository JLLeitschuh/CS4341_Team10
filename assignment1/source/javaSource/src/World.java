/**
 * Created by trietpham on 9/7/15.
 */

import java.io.*;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Scanner;
import java.util.InputMismatchException;


import java.util.StringTokenizer;
final public class World {
    private final int M;             // number of rows
    private final int N;             // number of columns
    private final double[][] data;   // M-by-N array

    // create M-by-N World of 0's
    public World(int M, int N) {
        this.M = M;
        this.N = N;
        data = new double[M][N];
    }

    // create World based on 2d array
    public World(double[][] data) {
        M = data.length;
        N = data[0].length;
        this.data = new double[M][N];
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                this.data[i][j] = data[i][j];
    }

    // copy constructor
    private World(World A) {
        this(A.data);
    }

    // create and return a random M-by-N World with values between 0 and 1
    public static World random(int M, int N) {
        World A = new World(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                A.data[i][j] = Math.random()*8+1;
        return A;
    }



    public void printWorld() {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++)
                System.out.printf("%9.0f ", data[i][j]);
            System.out.println();
        }
    }

//    public void writeTofile(){
//        try {
//            FileWriter outputFile = new FileWriter("/Users/trietpham/Desktop/b123.txt");
//            BufferedWriter bout = new BufferedWriter(outputFile);
//        World A = new World(M,N);
//        for (int i = 0; i < M; i++) {
//            for (int j = 0; j < N; j++) {
//                double total = A.data[i][j];
//                String total2 = String.valueOf(total);
//                bout.write(total2);
//                System.out.println("Done");
//            }
//        }
//        } catch (FileNotFoundException ex) {
//            ex.printStackTrace();
//        } catch (UnsupportedEncodingException ex) {
//            ex.printStackTrace();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    }

    // transfer M by N World to point array
//    public Point[] toPoint(){
//        int k = 0;
//        int t = M;
//        int h = N;
//        Point[] PointArray = new Point[t*h];
//        for (int i = 0; i < M;  i++){
//            for(int j = 0; j < N; j++){
//                Point tempPoint = new Point(i, j, (int)data[i][j]);
//                PointArray[k] = tempPoint;
//                k +=1;
//            }
//        }
//        return PointArray;
//    }



    // test client
    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);
        System.out.println("enter rows");
        int M = keyboard.nextInt();
        System.out.println("enter collums");
        int N = keyboard.nextInt();
        World A = World.random(M, N);

//        Point[] newPointArray = A.toPoint();
        A.printWorld();
        System.out.println();
        System.out.println("Start to write");
        try {
            FileWriter outputFile = new FileWriter("/Users/trietpham/Desktop/1234.txt");
            BufferedWriter bout = new BufferedWriter(outputFile);
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    double total = A.data[i][j];
                    String total2 = String.valueOf(total);
                    String[] num = total2.split("\\.");
                    String num1= num[0];
                    System.out.println(num1);
                    bout.write(num1+" ");
                }
                bout.write("\n");
            }
            bout.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }
//        A.toPoint();



}
