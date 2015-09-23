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
<<<<<<< HEAD
        for(int i =0; i <= 6; i++) {
//            this.bin1 = Float.parseFloat(input.get(i));
        }
//        this.bin2 = input.subList(7,14);
//        this.bin3 = input.subList(15,20);

=======
>>>>>>> 249ed3fbe26cc952a440f6b94bdb68942b433804
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






