public class Point {
    public final int x;
    public final int y;
    private final int cost;
    private boolean isGoal = false;
    private boolean isStart = false;

    /**
     * Copy Constructor
     * @param copyMe
     */
    public Point(Point copyMe){
        this.x = copyMe.x;
        this.y = copyMe.y;
        this.cost = copyMe.cost;
        this.isGoal = copyMe.isGoal;
        this.isStart = copyMe.isStart;
    }

    public Point(int x, int y, int cost){
        this.x = x;
        this.y = y;
        this.cost = cost;
    }

    /**
     * Generates a point using the token from the input file.
     * @param x
     * @param y
     * @param token
     */
    public Point(int x, int y, String token){
        this(x, y, 1);
        if(token.equals("G")){
            isGoal = true;
        } else if (token.equals("S")){
            isStart = true;
        } else {
            throw new InternalError("Token not valid " + token);
        }
    }

    /**
     * Do these two points refer to the same location?
     * @param p The point to compare to
     * @return True if the two points are refering to the same <code>x, y</code> location
     */
    public boolean isPoint(Point p){
        return this.isPoint(p.x, p.y);
    }

    /**
     * Is this point refering to this x, y location
     * @param x
     * @param y
     * @return True if this is the given x, y location.
     */
    public boolean isPoint(int x, int y){
        return this.x == x && this.y == y;
    }

    /**
     * @return True if this is the start.
     */
    public boolean isStart(){
        return this.isStart;
    }

    public boolean isGoal(){
        return this.isGoal;
    }

    /**
     * Gets the cost of the point. This will not be constant. It can be changed by exploding a localized set of points.
     * @return The cost of this point
     */
    public int getCost(){
        return this.cost;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                ", cost=" + cost +
                ", isGoal=" + isGoal +
                ", isStart=" + isStart +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (x != point.x) return false;
        if (y != point.y) return false;
        //if (cost != point.cost) return false;
        if (isGoal != point.isGoal) return false;
        return isStart == point.isStart;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        //result = 31 * result + cost;
        result = 31 * result + (isGoal ? 1 : 0);
        result = 31 * result + (isStart ? 1 : 0);
        return result;
    }
}
