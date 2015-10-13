package edu.wpi.cs4341.finalProject.learningAI;

import edu.wpi.cs4341.finalProject.ga.AbstractIndividual;
import edu.wpi.cs4341.finalProject.ga.AbstractPuzzle;
import edu.wpi.cs4341.finalProject.ga.Gene;

import java.util.ArrayList;
import java.util.List;

public class ReversiPuzzle extends AbstractPuzzle {


    public ReversiPuzzle() {
        super(generateGenePool());
    }

    @Override
    public List<AbstractIndividual> getIndividuals() {
        List<AbstractIndividual> initialPopulation = new ArrayList();
        for(int i = 0; i < getPopulationSize(); i++){
            initialPopulation.add(new ReversiPlayerIndividual(getRandomGeneSequence()));
        }
        return initialPopulation;
    }

    @Override
    public int getPopulationSize() {
        return 4;
    }

    public int getFixedGeneSequenceLength(){
        return 16;
    }

    /**
     * Generates a gene pool with 16
     * @return
     */
    private static final List<Gene<Integer>> generateGenePool(){
        List<Gene<Integer>> genePool = new ArrayList();
        for(int boardIndex = 0; boardIndex < 16; boardIndex++){
            for(int i = -100; i <= 100; i++){
                genePool.add(new Gene(new Integer(i)));
            }
        }
        return genePool;
    }
}
