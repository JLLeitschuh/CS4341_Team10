import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Neighbor {
    private Point cameFrom;
    private Point point;
    private Direction direction;
    private int costSoFar;
    private SquareGrid gridInstance;

    public Neighbor(Point cameFrom, Direction direction, SquareGrid gridInstance){

        this.cameFrom = cameFrom;
        this.point = direction.getDirectionLocation(cameFrom, gridInstance);
        this.direction = direction;
        this.costSoFar = this.point.getCost();
        this.gridInstance = gridInstance;
    }

    public boolean isPoint(Point p){
        return this.point.isPoint(p);
    }

    /**
     * SHOULD ONLY BE USED ONCE TO GENERATE THE INITIAL NODE
     * @param point The point this neighbor represents
     */
    public Neighbor(Point point, SquareGrid gridInstance){
        this.point = point;
        this.direction = Direction.NORTH;
        this.gridInstance = gridInstance;
    }

    public int getPriority(){
        //This is where the huristic will be added when it is implemented.
        return costSoFar;
    }

    public Point getPoint(){ return this.point;}

    public List<Neighbor> getNeighbors(Point id){
        List<Neighbor> neighborList = new ArrayList<Neighbor>();
        //If this point is the start then we can only go North
        if(this.point.isStart()) {
            for (Direction direction : Direction.values()) {
                neighborList.add(new Neighbor(id, direction, this.gridInstance));
            }
        } else {
            neighborList.add(new Neighbor(id, Direction.NORTH, this.gridInstance));
        }
        return neighborList;
    }

    public Collection<Neighbor> aStarSearch(Map<Neighbor, Integer> costSoFar){
        Collection<Neighbor> newSearchNodes = new ArrayList<>();
        for( Neighbor neighbor : this.getNeighbors(this.point)){
            int newCost = this.costSoFar + neighbor.point.getCost();
            if( !costSoFar.containsKey(neighbor) || newCost < neighbor.costSoFar){
                costSoFar.put(neighbor, newCost);
                newSearchNodes.add(neighbor);
            }
        }
        return newSearchNodes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Neighbor neighbor = (Neighbor) o;

        if (costSoFar != neighbor.costSoFar) return false;
        if (cameFrom != null ? !cameFrom.equals(neighbor.cameFrom) : neighbor.cameFrom != null) return false;
        if (!point.equals(neighbor.point)) return false;
        if (direction != neighbor.direction) return false;
        return gridInstance.equals(neighbor.gridInstance);

    }

    @Override
    public int hashCode() {
        int result = cameFrom != null ? cameFrom.hashCode() : 0;
        result = 31 * result + point.hashCode();
        result = 31 * result + direction.hashCode();
        result = 31 * result + costSoFar;
        result = 31 * result + gridInstance.hashCode();
        return result;
    }
}
