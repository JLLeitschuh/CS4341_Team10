import java.util.HashMap;
import java.util.Map;

public enum Direction {
    NORTH, EAST, SOUTH, WEST,
//    NORTH_BASH, EAST_BASH, SOUTH_BASH, WEST_BASH
    ;

    private static Map<Direction, Direction> oposites = new HashMap<Direction, Direction>();
    static {
        oposites.put(NORTH, SOUTH);
        oposites.put(EAST, WEST);
        oposites.put(SOUTH, NORTH);
        oposites.put(WEST, EAST);
    }

    String upArrow = ("↑");
    String downArrow = ("↓");
    String rightArrow = ("→");
    String leftArrow = ("←");
    String upArrowBash = ("⇈");
    String downArrowBash = ("⇊");
    String rightArrowBash = ("⇉");
    String leftArrowBash = ("⇇");

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

    public boolean isOposite(Direction comparison){
        return oposites.get(this) == comparison;
    }

    public double getCostMultiplier(Direction turnTo){
        if(this.equals(turnTo)){
            return 0;
        }
        switch (this){
            case NORTH:
            case EAST:
            case SOUTH:
            case WEST:
                if(isOposite(turnTo)){
                    return (2.0/3.0);
                } else {
                    return (1.0/3.0);
                }
            default:
                throw new Error("CASE NOT DEFINED FOR " + this);
        }

        //return 0;
    }

    @Override
    public String toString() {
        switch(this) {
            case NORTH:
                return upArrow;
            case EAST:
                return rightArrow;
            case SOUTH:
                return downArrow;
            case WEST:
                return leftArrow;
            default:
                return "No String";
        }
    }
}
