
public class Neighbor {
    private Point point;
    private Direction direction;
    private int priority;

    public Neighbor(Point originPoint, Direction direction){
        this.point = direction.getDirectionLocation(originPoint);
        this.direction = direction;
    }

    /**
     * SHOULD ONLY BE USED ONCE
     * @param point The point this neighbor represents
     */
    public Neighbor(Point point){
        this.point = point;
        this.direction = Direction.NORTH;
    }

    public int getPriority(){
        return priority;
    }

    public Point getPoint(){ return this.point;}

    public void makeOutside(){
        this.priority+= 100;
    }
}
