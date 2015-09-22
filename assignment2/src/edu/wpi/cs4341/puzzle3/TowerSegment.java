package edu.wpi.cs4341.puzzle3;

/**
 * Created by nhtranngoc on 9/21/15.
 */
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

    public String getSegmentType(){
        return this.segmentType;
    }

    public int getWidth(){
        return this.width;
    }
    
    public int getStrength(){
        return this.strength;
    }

    public int getCost(){
        return this.cost;
    }

    private String parseInput(String input, int select){
        String delim = "[, ]";
        return input.split(delim)[select];
    }
}
