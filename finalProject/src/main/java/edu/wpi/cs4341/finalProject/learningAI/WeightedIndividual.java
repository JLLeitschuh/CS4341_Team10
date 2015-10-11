package edu.wpi.cs4341.finalProject.learningAI;

import edu.wpi.cs4341.finalProject.ga.AbstractIndividual;
import edu.wpi.cs4341.finalProject.ga.Gene;

import java.util.*;

public class WeightedIndividual extends AbstractIndividual<Integer> {
    private final Integer targetValue;
    private static final Map<Coordinate, Integer> indexMap = new HashMap();
    static {
        indexMap.put(new Coordinate(0, 0), 0);
        indexMap.put(new Coordinate(1, 0), 1);
        indexMap.put(new Coordinate(0, 1), 2);
        indexMap.put(new Coordinate(1, 1), 3);
        indexMap.put(new Coordinate(0, 2), 4);
        indexMap.put(new Coordinate(2, 0), 5);
        indexMap.put(new Coordinate(2, 1), 6);
        indexMap.put(new Coordinate(1, 2), 7);
        indexMap.put(new Coordinate(2, 2), 8);
        indexMap.put(new Coordinate(3, 0), 9);
        indexMap.put(new Coordinate(0, 3), 10);
        indexMap.put(new Coordinate(3, 1), 11);
        indexMap.put(new Coordinate(1, 3), 12);
        indexMap.put(new Coordinate(3, 2), 13);
        indexMap.put(new Coordinate(3, 2), 14);
        indexMap.put(new Coordinate(3, 3), 15);
    }

    static class Coordinate {
        public final int x, y;
        Coordinate(final int x,final int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Coordinate that = (Coordinate) o;

            if (x != that.x) return false;
            return y == that.y;

        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }

    /**
     * @param geneSegments The gene segments that make up his individual.
     * @param targetValue The value that we want to hit in our gene.
     */
    public WeightedIndividual(LinkedHashSet<Gene<Integer>> geneSegments, Integer targetValue){
        super(geneSegments);
        this.targetValue = targetValue;
    }

    /**
     * Copy constructor for WeightedIndividual
     * @param individual The individual to copy
     */
    private WeightedIndividual(WeightedIndividual individual){
        super((LinkedHashSet<Gene<Integer>>) individual.geneSegments.clone());
        this.targetValue = individual.targetValue;
    }

    @Override
    public AbstractIndividual crossOver(AbstractIndividual otherIndividual) {
        return new WeightedIndividual(singlePointCrossover(otherIndividual), targetValue);
    }

    public Integer getWeightForBoardIndex(final int x, final int y){
        final int
                newX = Math.abs(x - 4),
                newY = Math.abs(y - 4);
        assert (newX < 4) : "The X index was too high";
        assert (newX >= 0): "The X index was too low";
        assert (newY < 4) : "The Y index was too high";
        assert (newY >= 0): "The Y index was too low";

        Integer index = indexMap.get(new Coordinate(newX, newY));
        assert (index != null) : "There was no defined mapping for point x: " + x + " y: " + y;
        return new ArrayList<>(getGeneSegments()).get(index).get();
    }

    @Override
    public WeightedIndividual copy(){
        return new WeightedIndividual(this);
    }

    @Override
    public float getFitness() {
        return 0; //for now.
    }
}
