import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Direction {
    NORTH(1, BaseAction.FORWARD), EAST(1, BaseAction.FORWARD), SOUTH(1, BaseAction.FORWARD), WEST(1, BaseAction.FORWARD),
    NORTH_BASH(2, BaseAction.BASH), EAST_BASH(2, BaseAction.BASH), SOUTH_BASH(2, BaseAction.BASH), WEST_BASH(2, BaseAction.BASH)
    ;
    private static final List<Direction> bashList = Arrays.asList(NORTH_BASH, EAST_BASH, WEST_BASH, SOUTH_BASH);
    private static final Map<Direction, List<Direction>> opposites = new HashMap<>();
    static {
        opposites.put(NORTH, Arrays.asList(SOUTH, SOUTH_BASH));
        opposites.put(EAST, Arrays.asList(WEST, WEST_BASH));
        opposites.put(SOUTH, Arrays.asList(NORTH, NORTH_BASH));
        opposites.put(WEST, Arrays.asList(EAST, EAST_BASH));
        opposites.put(NORTH_BASH, Arrays.asList(SOUTH, SOUTH_BASH));
        opposites.put(EAST_BASH, Arrays.asList(WEST, WEST_BASH));
        opposites.put(SOUTH_BASH, Arrays.asList(NORTH, NORTH_BASH));
        opposites.put(WEST_BASH, Arrays.asList(EAST, EAST_BASH));
    }
    private static final Map<Direction, Direction> equivalentDirection = new HashMap<>();
    static {
        equivalentDirection.put(NORTH, NORTH_BASH);
        equivalentDirection.put(EAST, EAST_BASH);
        equivalentDirection.put(SOUTH, SOUTH_BASH);
        equivalentDirection.put(WEST, WEST_BASH);
        equivalentDirection.put(NORTH_BASH, NORTH);
        equivalentDirection.put(EAST_BASH, EAST);
        equivalentDirection.put(SOUTH_BASH, SOUTH);
        equivalentDirection.put(WEST_BASH, WEST);
    }
    private static final Map<Direction, Direction> baseDirection = new HashMap<>();
    static {
        baseDirection.put(NORTH, NORTH);
        baseDirection.put(EAST, EAST);
        baseDirection.put(SOUTH, SOUTH);
        baseDirection.put(WEST, WEST);
        baseDirection.put(NORTH_BASH, NORTH);
        baseDirection.put(EAST_BASH, EAST);
        baseDirection.put(SOUTH_BASH, SOUTH);
        baseDirection.put(WEST_BASH, WEST);
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
            actions.add(BaseAction.FORWARD);
        }
        return actions;
    }

    private boolean isOpposite(Direction comparison){
        return opposites.get(this).contains(comparison);
    }

    private boolean isSameDirection(Direction comparison){
        if(this.equals(comparison)) return true;
        if(equivalentDirection.get(this).equals(comparison)) return true;
        return false;
    }

    private int getTurnCost(Direction turnTo, Point wasAt){
        if(this.isSameDirection(turnTo)){
            return 0;
        } else if(isOpposite(turnTo)){
            return (int)Math.ceil((2.0/3.0) * wasAt.getCost());
        } else {
            return (int)Math.ceil((1.0/3.0) * wasAt.getCost());
        }
    }

    public static boolean isBash(Direction turnTo){
        return bashList.contains(turnTo);
    }

    public int getCostToMove(Direction turnTo, Point wasAt, Point nowAt, SquareGrid gridInstance){
        //System.out.println("Comparing: " + this + " " + turnTo);
        return (isBash(turnTo) ? turnTo.getBasicDirection().getDirectionLocation(wasAt, gridInstance).getBashThroughCost()  : 0) + nowAt.getCost() + getTurnCost(turnTo, wasAt);
    }

    public Direction getBasicDirection(){
        return baseDirection.get(this);
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
