import java.util.List;

public class SquareGrid implements Cloneable {
    private final Point goal;
    private final Point start;
    private final int width;
    private final int height;
    private List<Point> points;

    public SquareGrid(int width, int height, List<Point> points){
        this.width = width;
        this.height = height;
        this.points = points;
        Point tempStart = null, tepGoal = null;
        boolean goalFound = false, startFound = false;

        // Find the start and the end goals
        for (Point point : points){
            if(point.isGoal()){
                if(goalFound) throw new Error("Can't have more than one goal");
                goalFound = true;
                tepGoal = point;
            } else if (point.isStart()){
                if(startFound) throw new Error("Can't have more than one start");
                startFound = true;
                tempStart = point;
            }
        }
        this.goal = tepGoal;
        this.start = tempStart;
        assert( this.goal != null);
        assert( this.start != null);
    }

    public Point getStart(){return this.start;}

    public Point getGoal(){return this.goal;}

    public int getWidth(){return this.width;}

    public int getHeight(){return this.height;}

    public boolean inBounds(Point id){
        return inBounds(id.x, id.y);
    }

    public boolean inBounds(int x, int y){
        return (((0 <= x) &&(x < this.width)) && ((0 <= y) && (y< this.height)));
    }

    /**
     * Checks if the point is staying within the map plus two spaces outside the borders.
     * @param id
     * @return
     */
    private boolean somewhatInBounds(Point id){
        return (((-2 <= id.x) && (id.x < this.width+2)) && ((-2 <= id.y) && (id.y< this.height+2)));
    }

    /**
     * Gets the given point from the grid. This is used because there will be multiple grid instances
     * when we start detonating.
     * @param x
     * @param y
     * @return The point stored in the square grid
     */
    public Point getPoint(int x, int y){
        for (Point p : points){
            if(p.isPoint(x, y)){
                return p;
            }
        }
        System.out.println("WARN: Point x:" + x + " y: " + y + " does not exist. It has been be instantiated");

        Point newPoint;
        if(inBounds(x, y)){
            newPoint = new Point(x, y, 0);
        } else {
            newPoint = new Point(x, y, 100);
        }

        if(!somewhatInBounds(newPoint)){
            throw new IndexOutOfBoundsException("Point x:" + x + " y:" + y + " is outside of the bounds of the grid and the walls");
        }
        points.add(newPoint);
        return newPoint;
    }


    /**
     * Allows us to clone this Square grid in its current state.
     * @return A copy of this square grid.
     */
    public SquareGrid clone(){
        try {
            return this.getClass().cast(super.clone());
        } catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return null;
    }

}
