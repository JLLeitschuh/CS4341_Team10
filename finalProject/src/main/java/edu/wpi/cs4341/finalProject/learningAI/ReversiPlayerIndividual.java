package edu.wpi.cs4341.finalProject.learningAI;

import edu.wpi.cs4341.finalProject.ReversiBoard;
import edu.wpi.cs4341.finalProject.ReversiBoard.TKind;
import edu.wpi.cs4341.finalProject.ga.AbstractIndividual;
import edu.wpi.cs4341.finalProject.ga.Gene;

import java.util.*;

import static edu.wpi.cs4341.finalProject.ReversiBoard.Move;

public class ReversiPlayerIndividual extends AbstractIndividual<Integer> {
    private static final Map<Coordinate, Integer> indexMap = new HashMap();
    static {
        /* 0,0 is the center four squares */
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
        indexMap.put(new Coordinate(2, 3), 14);
        indexMap.put(new Coordinate(3, 3), 15);
        /* 3,3 is the corners */
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

    private Optional<Integer> fitness = Optional.empty();
    /**
     * @param geneSegments The gene segments that make up his individual.
     */
    public ReversiPlayerIndividual(LinkedHashSet<Gene<Integer>> geneSegments){
        super(geneSegments);
    }

    /**
     * Copy constructor for ReversiPlayerIndividual
     * @param individual The individual to copy
     */
    private ReversiPlayerIndividual(ReversiPlayerIndividual individual){
        super((LinkedHashSet<Gene<Integer>>) individual.geneSegments.clone());
    }

    @Override
    public AbstractIndividual crossOver(AbstractIndividual otherIndividual) {
        return new ReversiPlayerIndividual(singlePointCrossover(otherIndividual));
    }

    public Integer getWeightForBoardIndex(final int x, final int y){
        final int
                newX = Math.abs(x - 4 > 0 ? x - 4 : x - 3),
                newY = Math.abs(y - 4 > 0 ? y - 4 : y - 3);
        assert (newX < 4) : "The X index was too high " + newX;
        assert (newX >= 0): "The X index was too low " + newX;
        assert (newY < 4) : "The Y index was too high " + + newY;
        assert (newY >= 0): "The Y index was too low " + newY;

        Integer index = indexMap.get(new Coordinate(newX, newY));
        assert (index != null) : "There was no defined mapping for point x: " + x + " y: " + y;
        return new ArrayList<>(getGeneSegments()).get(index).get();
    }

    @Override
    public ReversiPlayerIndividual copy(){
        return new ReversiPlayerIndividual(this);
    }

    private Integer playGame(){
        ReversiBoard board = new ReversiBoard();
        Move move = new Move();
        TKind.white.setPlayer(this);
        final TKind playAs = TKind.white;

        while (board.userCanMove(TKind.black) || board.userCanMove(TKind.white)) {
            if (board.findMove(TKind.black, 6, move))
                board.move(move, TKind.black);
            if (board.findMove(TKind.white, 6, move))
                board.move(move, TKind.white);
        }
        return board.getFitness(playAs);
    }

    @Override
    public float getFitness() {
        fitness = Optional.of(fitness.orElse(playGame()));
        return new Float(fitness.get());
    }
}
