/**
 * Created by nhtranngoc on 9/10/15.
 */

//Heuristic 6. Non admissible, returns 3*sqrt(hor^2 + ver^2)
public class Heuristic6 {
    public int calcHeuristic(Point current, Point goal){
        int horizontal = Math.abs(current.x - goal.x);
        int vertical = Math.abs(current.y - goal.y);
        double sqrHor = (double)(horizontal*horizontal);
        double sqrVer = (double)(vertical*vertical);
        return (int)(3* Math.sqrt(sqrHor + sqrVer));
    }
}
