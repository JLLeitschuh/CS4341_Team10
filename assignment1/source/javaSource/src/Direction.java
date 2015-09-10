import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Direction {
    NORTH, EAST, SOUTH, WEST,
    NORTH_BASH, EAST_BASH, SOUTH_BASH, WEST_BASH
    ;
    private static final List<Direction> bashList = Arrays.asList(NORTH_BASH, EAST_BASH, WEST_BASH, SOUTH_BASH);
    private static final Map<Direction, Direction> oposites = new HashMap<Direction, Direction>();
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

    public Point getDirectionLocation(Point currentLocation, SquareGrid gridInstance){
        switch(this){
            case NORTH: return gridInstance.getPoint(currentLocation.x, currentLocation.y - 1);
            case EAST:  return gridInstance.getPoint(currentLocation.x + 1, currentLocation.y);
            case SOUTH: return gridInstance.getPoint(currentLocation.x, currentLocation.y + 1);
            case WEST:  return gridInstance.getPoint(currentLocation.x - 1, currentLocation.y);
            case NORTH_BASH: return gridInstance.getPoint(currentLocation.x, currentLocation.y + 2);
            case EAST_BASH: return gridInstance.getPoint(currentLocation.x+2, currentLocation.y);
            case SOUTH_BASH: return gridInstance.getPoint(currentLocation.x, currentLocation.y-2);
            case WEST_BASH: return gridInstance.getPoint(currentLocation.x-2, currentLocation.y);
            default: System.err.print("Undefined Direction");
        }
        return null;
    }

    private boolean isOpposite(Direction comparison){
        return oposites.get(this) == comparison;
    }

    private int getTurnCost(Direction turnTo, Point nowAt){
        if(!this.isBash(turnTo) && this.equals(turnTo)){
            return 0;
        } else if (isBash(turnTo) && isBash(this)) { // Bashing in the same direction as before
            throw new Error("Can't bash twice in a row");
        }
        if(isOpposite(turnTo)){
            return (int)Math.ceil((2.0/3.0) * nowAt.getCost());
        } else {
            return (int)Math.ceil((1.0/3.0) * nowAt.getCost());
        }
    }

    public static boolean isBash(Direction turnTo){
        return bashList.contains(turnTo);
    }

    public int getCostMultiplier(Direction turnTo, Point nowAt){
        return getTurnCost(turnTo, nowAt);
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
