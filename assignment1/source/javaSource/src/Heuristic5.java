/**
 * Created by nhtranngoc on 9/10/15.
 */

//Heuristic 5. Returns admissible heuristic of n, which is sqrt(hor^2 + ver^2);
public class Heuristic5 implements IHeuristic {
    public int calcHeuristic(Point current, Point goal){
        int horizontal = Math.abs(current.x - goal.x);
        int vertical = Math.abs(current.y - goal.y);
        double sqrHor = (double)(horizontal*horizontal);
        double sqrVer = (double)(vertical*vertical);
        return (int)Math.sqrt(sqrHor + sqrVer);
    }
}
