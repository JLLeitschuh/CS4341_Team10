/**
 * Created by trietpham on 9/7/15.
 */
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
    // transfer M by N World to point array
    public Point[] toPoint(){
        int k = 0;
        int t = M;
        int h = N;
        Point[] PointArray = new Point[t*h];
        for (int i = 0; i < M;  i++){
            for(int j = 0; j < N; j++){
                Point tempPoint = new Point(i, j, (int)data[i][j]);
                PointArray[k] = tempPoint;
                k +=1;
            }
        }
        return PointArray;
    }

    // test client
    public static void main(String[] args) {
        World A = World.random(4, 4);
        Point[] newPointArray = A.toPoint();
        A.printWorld();
        A.toPoint();
        System.out.println();
    }
}