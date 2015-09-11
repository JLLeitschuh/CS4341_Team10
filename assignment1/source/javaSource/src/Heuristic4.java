/**
 * Created by nhtranngoc on 9/10/15.
 */

//Heuristic 4. Return sum of horizontal and vertical.
public class Heuristic4 implements IHeuristic {
    public int calcHeuristic(Point current, Point goal){
        int horizontal = Math.abs(current.x - goal.x);
        int vertical = Math.abs(current.y - goal.y);
        return horizontal + vertical;
    }
}
