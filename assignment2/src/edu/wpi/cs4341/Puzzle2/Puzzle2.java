package edu.wpi.cs4341.Puzzle2;

import edu.wpi.cs4341.ga.AbstractIndividual;
import edu.wpi.cs4341.ga.AbstractPuzzle;
import edu.wpi.cs4341.ga.Gene;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Puzzle2 extends AbstractPuzzle<Integer> {
    LinkedList<String> bin1 = new LinkedList<>();
    LinkedList<String> bin2 = new LinkedList<>();
    LinkedList<String> bin3 = new LinkedList<>();
    private final List<AbstractIndividual> initialPopulation;
    private final int POPULATIONSIZE = 20;
//Store each ten genes in each three bin
    public Puzzle2(List<String> input) {
        super(createGenePool(input));
        for(int i =0; i <= 6; i++) {
//            this.bin1 = Float.parseFloat(input.get(i));
        }
//        this.bin2 = input.subList(7,14);
//        this.bin3 = input.subList(15,20);
        this.bin1 = new LinkedList<>(input.subList(0, 9));
        this.bin2 = new LinkedList<>(input.subList(10, 19));
        this.bin3 = new LinkedList<>(input.subList(20, 30));

        this.initialPopulation = new ArrayList<>();

        // fill the initial population with individuals, with random gene sequences
        for (int i = 0; i < POPULATIONSIZE; i++) {
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

    private static List<Gene<Integer>> createGenePool(List<String> input) {
        List<Gene<Integer>> geneList = new ArrayList<>();
        List<String> geneStringList = input.subList(1, input.size());
        for (String geneString : geneStringList) {
            geneList.add(new Gene<Integer>(new Integer(String.valueOf(geneString))));
        }
        return geneList;
    }
    //Multiply each gene in bin1 together
    private static void getMultipliedBin1(List<String> bin1) {
        List<Float> result_Multi = new ArrayList<>();

        for (int i = 0; i < bin1.size(); i++) {
            result_Multi.add(
                    Float.parseFloat(bin1.get(i)) * Float.parseFloat(bin1.get(i+1)));
        }
    }
    //Add each gene in bin1 together
    private static void getSumBin2(List<String> bin2) {
        List<Float> result_Sum = new ArrayList<>();

        for (int i = 0; i < bin2.size(); i++) {
            result_Sum.add(
                    Float.parseFloat(bin2.get(i)) + Float.parseFloat(bin2.get(i+1)));

        }
    }

    private static void getIgnoredBin3(List<String> bin3) {
        List<String> result_Ignored = new ArrayList<>();

        for (int i = 0; i < bin3.size(); i++) {
            result_Ignored.add(bin3.get(i));
            ;

        }
    }


}






