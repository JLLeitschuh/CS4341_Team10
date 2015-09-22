package edu.wpi.cs4341.puzzle1;

import edu.wpi.cs4341.ga.AbstractIndividual;
import edu.wpi.cs4341.ga.AbstractPuzzle;
import edu.wpi.cs4341.ga.Gene;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Puzzle1 extends AbstractPuzzle<Integer> {
    private final Integer targetValue;
    private final List<AbstractIndividual> initialPopulation;
    private final int POPULATIONSIZE = 20;

    public Puzzle1(List<String> input) {
        super(createGenePool(input));
        this.targetValue = new Integer(input.get(0));
        this.initialPopulation = new ArrayList<>();

        // fill the initial population with individuals, with random gene sequences
        for(int i = 0; i < POPULATIONSIZE; i++) {
             this.initialPopulation.add(new PackedIndividual(getRandomGeneSequence(), targetValue));
        }

    }

    public Integer getTargetValue() {
        return this.targetValue;
    }

    @Override
    public List<AbstractIndividual> getIndividuals() {
        return initialPopulation;
    }

    @Override
    public int getFixedGeneSequenceLength() {
        return AbstractPuzzle.NO_FIXED_GENE_SEQUENCE_LENGTH;
    }

    @Override
    public int getPopulationSize() {
        return this.POPULATIONSIZE;
    }

    private static List<Gene<Integer>> createGenePool(List<String> input){
        List<Gene<Integer>> geneList = new ArrayList<>();
        List<String> geneStringList = input.subList(1, input.size());
        for(String geneString : geneStringList) {
            geneList.add(new Gene<Integer>(new Integer(geneString)));
        }
        return geneList;
    }


}
