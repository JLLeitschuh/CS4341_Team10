/**
 * Created by trietpham on 9/7/15.
 */

import java.io.*;
import java.util.Scanner;
import java.util.Random;

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
            for (int j = 0; j < N; j++) {
                if (data[i][j] == 10) {
                    System.out.printf("        S ");
                }
                else if (data[i][j] == 11){
                   System.out.printf("        G ");

                }
                else {
                    System.out.printf("%9.0f ", data[i][j]);
                }
            }
                System.out.println();
        }
    }

//
    // test client
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("enter rows");
        int M = keyboard.nextInt();
        System.out.println("enter collums");
        int N = keyboard.nextInt();
        World A = World.random(M, N);
        Random rand = new Random();
        int g = rand.nextInt(M-1)+1;
        int h = rand.nextInt(N-1)+1;
        A.data[g][h]=10;
        int k = rand.nextInt(M-1)+1;
        int l = rand.nextInt(N-1)+1;
        A.data[k][l]=11;
        A.printWorld();
        System.out.println();
        System.out.println("Start to write");
        try {
            FileWriter outputFile = new FileWriter("/Users/trietpham/Desktop/CS4341_Team10/assignment1/source/javaSource/src/board_5.txt");
            BufferedWriter bout = new BufferedWriter(outputFile);
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    double total = A.data[i][j];
                    if (total != 10 && total != 11) {
                        String total2 = String.valueOf(total);
                        String[] num = total2.split("\\.");
                        String num1 = num[0];
                        System.out.println(num1);
                        bout.write(num1 + " ");
                    }
                    else if (total == 10){
                        bout.write("S ");
                    }
                    else {
                        bout.write("G ");
                    }

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

}
