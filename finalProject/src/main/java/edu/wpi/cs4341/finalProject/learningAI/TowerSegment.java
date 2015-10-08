package edu.wpi.cs4341.puzzle3;

public class TowerSegment {
    private String segmentType;
    private int width;
    private int strength;
    private int cost;

    public TowerSegment(String input){
        this.segmentType = parseInput(input, 0);
        this.width = Integer.parseInt(parseInput(input, 1));
        this.strength = Integer.parseInt(parseInput(input, 2));
        this.cost = Integer.parseInt(parseInput(input, 3));
    }

    /**
     * Determines if this tower can support a given number of segments on top of it.
     * @param countOnTop The number of elements on top of this one.
     * @return true if this segments strength can hold that number of elements on top of it.
     */
    public boolean canSupportOnTop(int countOnTop){
        return countOnTop <= strength;
    }

    /**
     * Checks if this tower is wide enough to support a given segment on top of it.
     * @param next The next element that this one needs to support
     * @return true if this element can support the next element.
     */
    public boolean canSupportOnTop(TowerSegment next){
        return this.width >= next.width;
    }

    /**
     * Gets the cost of this tower segment.
     * @return The cost of this segment.
     */
    public int getCost(){
        return this.cost;
    }

    private String parseInput(String input, int select){
        String delim = ", ";
//        String out[] = input.split(delim);
        return input.split(delim)[select];
    }

    /**
     * @return true if this is a valid top segment
     */
    public boolean isValidTop(){
        return segmentType.equals("Lookout");
    }

    /**
     * @return true if this is a valid middle segment
     */
    public boolean isValidMiddle(){
        return segmentType.equals("Wall");
    }

    /**
     * @return true if this is a valid bottom segement
     */
    public boolean isValidBase(){
        return segmentType.equals("Door");
    }

    @Override
    public String toString(){
        return "TowerSegment{" +
                "Type= " + segmentType +
                " | Width= " + width +
                " | Strength= " + strength +
                " | Cost= " + cost +
                '}';
    }

}
