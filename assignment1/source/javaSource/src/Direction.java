
public enum Direction {
    NORTH, EAST, SOUTH, WEST;

    public Point getDirectionLocation(Point currentLocation){
        switch(this){
            case NORTH: return Point.getPoint(currentLocation.x, currentLocation.y + 1);
            case EAST: return Point.getPoint(currentLocation.x+1, currentLocation.y);
            case SOUTH: return Point.getPoint(currentLocation.x, currentLocation.y-1);
            case WEST: return Point.getPoint(currentLocation.x-1, currentLocation.y);
            default: System.err.print("Undefined Direction");
        }
        return null;
    }
}
