package edu.wpi.cs4341.finalProject.learningAI;

import edu.wpi.cs4341.finalProject.ga.AbstractIndividual;
import edu.wpi.cs4341.finalProject.ga.AbstractPuzzle;
import edu.wpi.cs4341.finalProject.ga.Gene;

import java.util.ArrayList;
import java.util.List;


public class LearningAI extends AbstractPuzzle<TowerSegment> {
    private final List<AbstractIndividual> initialPopulation;
    private final int POPULATIONSIZE = 20;

    public LearningAI(List<String> input) {
        super(createGenePool(input));
        this.initialPopulation = new ArrayList<>();
        // fill the initial population with individuals, with random gene sequences
        for(int i = 0; i < POPULATIONSIZE; i++) {
            this.initialPopulation.add(new TowerIndividual(getRandomGeneSequence()));
        }
    }


    public List<AbstractIndividual> getIndividuals(){
        return initialPopulation;
    }

    public int getPopulationSize() {
        return this.POPULATIONSIZE;
    }

    private static List<Gene<TowerSegment>> createGenePool(List<String> input){
        List<Gene<TowerSegment>> geneList = new ArrayList<>();
        List<String> geneStringList = input.subList(0, input.size());
        for(String geneString : geneStringList) {
            geneList.add(new Gene<TowerSegment>(new TowerSegment(geneString)));
        }
        return geneList;
    }
}
