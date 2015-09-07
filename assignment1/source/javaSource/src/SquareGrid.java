import java.util.ArrayList;
import java.util.List;

/**
 * Created by nhtranngoc on 9/7/15.
 */
public class SquareGrid {
    private int width;
    private int height;

    public SquareGrid(int width, int height){
        this.width = width;
        this.height = height;
    }

    public int getWidth(){return this.width;}

    public int getHeight(){return this.height;}

    public boolean inBounds(Point id){
        return (((0 <= id.x) &&(id.x < this.width)) && ((0 <= id.y) && (id.y< this.height)));
    }

    public List<Neighbor> getNeighbors(Point id){
        List<Neighbor> neighborList = new ArrayList<Neighbor>();
        neighborList.add(new Neighbor(id, Direction.NORTH));
        neighborList.add(new Neighbor(id, Direction.EAST));
        neighborList.add(new Neighbor(id, Direction.WEST));
        neighborList.add(new Neighbor(id, Direction.SOUTH));
        for (Neighbor neighbor: neighborList){
            if (!inBounds(neighbor.getPoint())){
                neighbor.makeOutside();
            }
        }
        return neighborList;
    }

}
