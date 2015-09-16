package edu.wpi.cs4341.puzzle1;

import edu.wpi.cs4341.ga.AbstractIndividual;
import edu.wpi.cs4341.ga.AbstractPuzzle;
import edu.wpi.cs4341.ga.IGene;

import java.util.Collections;
import java.util.List;

public class Puzzle1 extends AbstractPuzzle {
    private final List<AbstractIndividual> initialPopulation;

    public Puzzle1(List<String> input) {
        super(createGenePool(input));
        assert false;
        initialPopulation = null;
    }

    @Override
    public List<AbstractIndividual> getIndividuals() {
        return Collections.unmodifiableList(initialPopulation);
    }

    private static List<IGene> createGenePool(List<String> input){
        assert false;
        return null;
    }
}
