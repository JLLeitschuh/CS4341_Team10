public class Point {
    public final int x;
    public final int y;
    private final int cost;
    private boolean isGoal = false;
    private boolean isStart = false;

    public Point(int x, int y, int cost){
        this.x = x;
        this.y = y;
        this.cost = cost;
    }

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

    public boolean isPoint(Point p){
        return this.isPoint(p.x, p.y);
    }

    public boolean isPoint(int x, int y){
        return this.x == x && this.y == y;
    }

    public boolean isStart(){
        return this.isStart;
    }

    public boolean isGoal(){
        return this.isGoal;
    }

    public int getCost(){
        return this.cost;
    }
}
