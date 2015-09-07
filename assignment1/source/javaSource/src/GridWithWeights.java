/**
 * Created by nhtranngoc on 9/7/15.
 */
public class GridWithWeights extends SquareGrid{
    public int weights;

    public GridWithWeights(int width, int height, int weights){
        super(width, height);
        this.weights=weights;
    }

    public int getCost(Point from, Point to){
        return 0;
    }

    public int putWeight(Point loc, int weights){
        return 0;
    }
}
