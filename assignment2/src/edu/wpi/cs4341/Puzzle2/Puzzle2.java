package edu.wpi.cs4341.Puzzle2;

import edu.wpi.cs4341.ga.AbstractIndividual;
import edu.wpi.cs4341.ga.AbstractPuzzle;
import edu.wpi.cs4341.ga.Gene;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Puzzle2 extends AbstractPuzzle<Integer> {
    List<Float> bin1 = new LinkedList<>();
    List<Float> bin2 = new LinkedList<>();
    List<Float> bin3 = new LinkedList<>();
    private final List<AbstractIndividual> initialPopulation;
    private final int POPULATIONSIZE = 20;

    public Puzzle2(List<String> input) {
        super(createGenePool(input));
        for(int i =0; i <= 6; i++) {
            this.bin1 = Float.parseFloat(input.get(i));
        }
        this.bin2 = input.subList(7,14);
        this.bin3 = input.subList(15,20);

        this.initialPopulation = new ArrayList<>();

        // fill the initial population with individuals, with random gene sequences
        for(int i = 0; i < POPULATIONSIZE; i++) {
//            this.initialPopulation.add(new PackedIndividual(getRandomGeneSequ);
        }

        }
    public void bin1_multiply(){
    }
    public void bin2_sum(){

    }
    public void bin3_ignore(){

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
