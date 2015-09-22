package edu.wpi.cs4341.Puzzle2;

import edu.wpi.cs4341.ga.AbstractIndividual;
import edu.wpi.cs4341.ga.AbstractPuzzle;
import edu.wpi.cs4341.ga.Gene;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Puzzle2 extends AbstractPuzzle<Integer> {
    LinkedList<Integer> bin1 = new LinkedList<Integer>();
    LinkedList<Integer> bin2 = new LinkedList<Integer>();
    LinkedList<Integer> bin3 = new LinkedList<Integer>();
    private final List<AbstractIndividual> initialPopulation;
    private final int POPULATIONSIZE = 20;

    public Puzzle2(List<String> input) {
        super(createGenePool(input));
        this.initialPopulation = new ArrayList<>();

        // fill the initial population with individuals, with random gene sequences
        for(int i = 0; i < POPULATIONSIZE; i++) {
//            this.initialPopulation.add(new PackedIndividual(getRandomGeneSequ);
        }

    }


    @Override
    public List<AbstractIndividual> getIndividuals() {
        return initialPopulation;
    }

    @Override
    public int getFixedGeneSequenceLength() {
        return -1;
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
