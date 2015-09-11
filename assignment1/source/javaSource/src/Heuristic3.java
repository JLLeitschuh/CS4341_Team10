/**
 * Created by nhtranngoc on 9/10/15.
 */

//Heuristic3. Return max(horizontal, vertical
public class Heuristic3 implements IHeuristic {
    public int calcHeuristic(Point current, Point goal){
        int horizontal = Math.abs(current.x - goal.x);
        int vertical = Math.abs(current.y - goal.y);
        return Math.max(horizontal, vertical);
    }
}
