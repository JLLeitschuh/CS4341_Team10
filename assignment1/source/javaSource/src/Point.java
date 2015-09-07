import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonathanleitschuh on 9/7/15.
 */
public class Point {
    private static List<Point> points = new ArrayList<Point>();
    public final int x;
    public final int y;

    private Point(int x, int y){
        this.x = x;
        this.y = y;
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
        Point newPoint = new Point(x, y);
        points.add(newPoint);
        return newPoint;
    }
}
