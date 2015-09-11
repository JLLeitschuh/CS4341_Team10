import java.util.*;

public class Neighbor {
    private final Neighbor cameFrom;
    private final Point point;
    private final Direction direction;
    private final SquareGrid gridInstance;
    private final int baseCost;
    private int priority;

    public Neighbor(Neighbor cameFrom, Direction direction, SquareGrid gridInstance){
        this.cameFrom = cameFrom;
        this.point = direction.getDirectionLocation(cameFrom.getPoint(), gridInstance);
        this.direction = direction;
        this.baseCost = cameFrom.direction.getCostToMove(direction, this.point);
        this.priority = this.baseCost;
        this.gridInstance = gridInstance;
    }

    /**
     * SHOULD ONLY BE USED ONCE TO GENERATE THE INITIAL NODE
     * @param point The point this neighbor represents
     */
    public Neighbor(Point point, SquareGrid gridInstance){
        this.cameFrom = null;
        this.point = point;
        //Initial Node facing direction
        this.direction = Direction.NORTH;
        this.gridInstance = gridInstance;
        this.baseCost = this.point.getCost();
        this.priority = this.point.getCost();
    }

    public Direction getDirection() { return  this.direction; }


    public boolean isPoint(Point p){
        return this.point.isPoint(p);
    }

    /**
     * This determines the priority in which this neighbor is in the frontier
     * @return The priority with which this element should be added to the frontier
     */
    public int getPriority(){
        return priority;
    }

    public Point getPoint(){ return this.point;}

    public List<Neighbor> getNeighbors(){
        List<Neighbor> neighborList = new ArrayList<Neighbor>();
        //If this point is the start then we can only go North
        if(this.point.isStart()) {
            System.out.println("Returning as start");
            neighborList.add(new Neighbor(this, Direction.NORTH, this.gridInstance));
            neighborList.add(new Neighbor(this, Direction.NORTH_BASH, this.gridInstance));
        } else {
            for (Direction direction : Direction.values()) {
                // This prevents two bashes in a row
                if(Direction.isBash(this.direction) && Direction.isBash(direction)){
                    continue;
                }
                neighborList.add(new Neighbor(this, direction, this.gridInstance));
            }
        }
        return neighborList;
    }

    private List<BaseAction> getActions(List<BaseAction> actions){
        if(this.cameFrom != null){
            this.cameFrom.direction.getAction(actions, this.direction);
            return this.cameFrom.getActions(actions);
        } else {
            return actions;
        }
    }

    public List<BaseAction> getActions(){
        List<BaseAction> actions = new ArrayList<>();
        actions = getActions(actions);
        Collections.reverse(actions);
        return actions;
    }

    /**
     * Performs an a star search on this neighbor
     * @param costSoFar A map of the cost so far for a given neighbor.
     * @return
     */
    public Collection<Neighbor> aStarSearch(Map<Neighbor, Integer> costSoFar, IHeuristic heuristic){
        Collection<Neighbor> newSearchNodes = new ArrayList<>();
        for( Neighbor next : this.getNeighbors()){
            System.out.println(this.point);
            System.out.println("Looking at: " + next.point.toString());

            // Print all of the elements in the cost so far list
            costSoFar.keySet().forEach(neighbor -> System.out.println("\tContains: " + neighbor));

            int newCost = costSoFar.get(this)
                    + this.baseCost;
            if( !costSoFar.containsKey(next) || newCost < costSoFar.get(next)){
                this.priority = newCost + heuristic.calcHeuristic(this.point, gridInstance.getGoal());
                costSoFar.put(next, newCost);
                newSearchNodes.add(next);
            }
        }
        return newSearchNodes;
    }

    /**
     * Recursively searches through the <code>cameFrom</code> Neighbors to
     * find the start node. Denoted by the null came from field. (This could be better)
     * @param path The existing list of path elements.
     * @return The path with this element added to it.
     */
    private List<Neighbor> getPath(List<Neighbor> path){
        path.add(this);
        if(this.cameFrom != null){
            return this.cameFrom.getPath(path);
        } else {
            return path;
        }
    }

    /**
     * Gets the path that it took to get to this neighbor.
     * @return A list of nodes, in order, required to reach this node.
     */
    public List<Neighbor> getPath(){
        List<Neighbor> path = new ArrayList<>();
        getPath(path);
        Collections.reverse(path);
        return path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Neighbor neighbor = (Neighbor) o;

        //if (priority != neighbor.priority) return false; //XXX: This is intentionally left out!
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
        //result = 31 * result + priority; //XXX: This is intentionally left out!
        result = 31 * result + gridInstance.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Neighbor{" +
                "cameFrom=" + cameFrom +
                ", point=" + point +
                ", direction=" + direction +
                ", priority=" + priority +
                ", gridInstance=" + gridInstance +
                '}';
    }
}
