package edu.wpi.cs4341.Puzzle2;

import edu.wpi.cs4341.ga.AbstractIndividual;
import edu.wpi.cs4341.ga.AbstractPuzzle;
import edu.wpi.cs4341.ga.Gene;
import edu.wpi.cs4341.puzzle1.PackedIndividual;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Puzzle2 extends AbstractPuzzle<Float> {

    private final List<AbstractIndividual> initialPopulation;
    private final int POPULATIONSIZE = 20;


    public Puzzle2(List<String> input) {
        super(createGenePool(input));
        this.initialPopulation = new ArrayList<>();

        // fill the initial population with individuals, with random gene sequences
        for(int i = 0; i < POPULATIONSIZE; i++) {
            this.initialPopulation.add(new BinsIndividual(getRandomGeneSequence()));
        }

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

    private static List<Gene<Float>> createGenePool(List<String> input) {
        List<Gene<Float>> geneList = new ArrayList<>();
        for (String geneString : input) {
            geneList.add(new Gene<Float> (new Float( String.valueOf(geneString) )));
        }
        return geneList;
    }


}






