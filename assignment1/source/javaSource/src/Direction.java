import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Direction {
    NORTH(1, BaseAction.FORWARD), EAST(1, BaseAction.FORWARD), SOUTH(1, BaseAction.FORWARD), WEST(1, BaseAction.FORWARD),
    NORTH_BASH(2, BaseAction.BASH), EAST_BASH(2, BaseAction.BASH), SOUTH_BASH(2, BaseAction.BASH), WEST_BASH(2, BaseAction.BASH)
    ;
    private static final List<Direction> bashList = Arrays.asList(NORTH_BASH, EAST_BASH, WEST_BASH, SOUTH_BASH);
    private static final Map<Direction, Direction> opposites = new HashMap<>();
    static {
        opposites.put(NORTH, SOUTH);
        opposites.put(EAST, WEST);
        opposites.put(SOUTH, NORTH);
        opposites.put(WEST, EAST);
    }

    public final int moveTotal;
    public final BaseAction baseAction;
    Direction(int moveTotal, BaseAction action){
        this.moveTotal = moveTotal;
        this.baseAction = action;
    }

    String upArrow = ("↑");
    String downArrow = ("↓");
    String rightArrow = ("→");
    String leftArrow = ("←");
    String upArrowBash = ("⇈");
    String downArrowBash = ("⇊");
    String rightArrowBash = ("⇉");
    String leftArrowBash = ("⇇");

    /**
     *
     * @param currentLocation
     * @param gridInstance
     * @return
     */
    public Point getDirectionLocation(Point currentLocation, SquareGrid gridInstance){
        switch(this){
            case NORTH: return gridInstance.getPoint(currentLocation.x, currentLocation.y - 1);
            case EAST:  return gridInstance.getPoint(currentLocation.x + 1, currentLocation.y);
            case SOUTH: return gridInstance.getPoint(currentLocation.x, currentLocation.y + 1);
            case WEST:  return gridInstance.getPoint(currentLocation.x - 1, currentLocation.y);
            case NORTH_BASH: return gridInstance.getPoint(currentLocation.x, currentLocation.y - 2);
            case EAST_BASH: return gridInstance.getPoint(currentLocation.x + 2, currentLocation.y);
            case SOUTH_BASH: return gridInstance.getPoint(currentLocation.x, currentLocation.y + 2);
            case WEST_BASH: return gridInstance.getPoint(currentLocation.x - 2, currentLocation.y);
            default: System.err.print("Undefined Direction");
        }
        return null;
    }

    public List<BaseAction> getAction(List<BaseAction> actions, Direction next) {
        if (isBash(next)) {
            actions.add(BaseAction.BASH);
            actions.add(BaseAction.FORWARD);
        } else if (this.equals(next)) {
            actions.add(BaseAction.FORWARD);
        } else {
            actions.add(BaseAction.TURN);
        }
        return actions;
    }

    private boolean isOpposite(Direction comparison){
        return opposites.get(this) == comparison;
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

    public int getCostToMove(Direction turnTo, Point nowAt){
        System.out.println("Comparing: " + this + " " + turnTo);
        return isBash(turnTo) ? getTurnCost(turnTo, nowAt) + 3 : getTurnCost(turnTo, nowAt) + nowAt.getCost();
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
            case NORTH_BASH:
                return upArrowBash;
            case EAST_BASH:
                return rightArrowBash;
            case SOUTH_BASH:
                return downArrowBash;
            case WEST_BASH:
                return leftArrowBash;
            default:
                return "No String";
        }
    }
}
