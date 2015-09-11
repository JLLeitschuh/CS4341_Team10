/**
 * Created by nhtranngoc on 9/10/15.
 */

//Heuristic 2. Return min(vertical, horizontal).
public class Heuristic2 implements IHeuristic {

    public int calcHeuristic(Point current, Point goal){
        int horizontal = Math.abs(current.x - goal.x);
        int vertical = Math.abs(current.y - goal.y);
        return Math.min(horizontal, vertical);
    }
}
