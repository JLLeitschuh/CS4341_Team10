import java.util.ArrayList;
import java.util.List;

public class Point {
    private static List<Point> points = new ArrayList<Point>();
    public final int x;
    public final int y;
    public final int weight;

    public Point(int x, int y, int weight){
        this.x = x;
        this.y = y;
        this.weight = weight;
        points.add(this);
    }

    boolean isPoint(int x, int y){
        return this.x == x && this.y == y;
    }

    public static Point getPoint(int x, int y){
        for (Point p : points){
            if(p.isPoint(x, y)){
                return p;
            }
        }
        throw new IndexOutOfBoundsException("Point x:" + x + " y:" + y + " does not exist. It must be instantiated");
    }
}
