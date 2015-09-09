
public enum Direction {
    NORTH, EAST, SOUTH, WEST,;
    //NORTH_BASH, EAST_BASH, SOUTH_BASH, WEST_BASH;

    public Point getDirectionLocation(Point currentLocation, SquareGrid gridInstance){
        switch(this){
            case NORTH: return gridInstance.getPoint(currentLocation.x, currentLocation.y - 1);
            case EAST:  return gridInstance.getPoint(currentLocation.x + 1, currentLocation.y);
            case SOUTH: return gridInstance.getPoint(currentLocation.x, currentLocation.y + 1);
            case WEST:  return gridInstance.getPoint(currentLocation.x - 1, currentLocation.y);
//            case NORTH_BASH: return gridInstance.getPoint(currentLocation.x, currentLocation.y + 2);
//            case EAST_BASH: return gridInstance.getPoint(currentLocation.x+2, currentLocation.y);
//            case SOUTH_BASH: return gridInstance.getPoint(currentLocation.x, currentLocation.y-2);
//            case WEST_BASH: return gridInstance.getPoint(currentLocation.x-2, currentLocation.y);
            default: System.err.print("Undefined Direction");
        }
        return null;
    }

}
