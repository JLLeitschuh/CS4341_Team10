package edu.wpi.cs4341.puzzle1;

import edu.wpi.cs4341.ga.AbstractIndividual;
import edu.wpi.cs4341.ga.AbstractPuzzle;
import edu.wpi.cs4341.ga.Gene;

import java.util.Collections;
import java.util.List;

public class Puzzle1 extends AbstractPuzzle {
    private final Integer targetValue;
    private final List<AbstractIndividual> initialPopulation;

    public Puzzle1(List<String> input) {
        super(createGenePool(input));
        this.targetValue = new Integer(input.get(0));
        assert false;
        initialPopulation = null;
    }

    @Override
    public List<AbstractIndividual> getIndividuals() {
        return Collections.unmodifiableList(initialPopulation);
    }

    private static List<Gene> createGenePool(List<String> input){
        List<String> geneStringList = input.subList(1, input.size());
        
        return null;

    }
}
