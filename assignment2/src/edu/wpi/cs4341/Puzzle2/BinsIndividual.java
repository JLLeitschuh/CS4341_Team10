package edu.wpi.cs4341.Puzzle2;

import edu.wpi.cs4341.ga.AbstractIndividual;
import edu.wpi.cs4341.ga.AbstractPuzzle;
import edu.wpi.cs4341.ga.Gene;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;

public class BinsIndividual extends AbstractIndividual {

    private LinkedHashSet<Gene> bin;

    public BinsIndividual(LinkedHashSet<Gene> geneSegments){
        super(geneSegments);
    }


    @Override
    public AbstractIndividual crossOver(AbstractIndividual otherIndividual) {
        return new BinsIndividual(singlePointCrossover(otherIndividual));
    }

    @Override
    public BinsIndividual copy(){
        return null;
    }

    @Override
    public float getFitness() {
        float currentFitness = 0;
        List<Gene<Float>> myGenes = new ArrayList(this.geneSegments);
        for (Gene<Float> gene : myGenes.subList(0,9)) {
            currentFitness *= gene.get();
        }
        for (Gene<Float> gene : myGenes.subList(10,19)) {
            currentFitness += gene.get();
        }
        return currentFitness;
    }

    @Override
    public void mutate(AbstractPuzzle puzzleRules){

        Random randomGenerator = new Random();
        List<Gene> myGenes = new ArrayList<>(this.geneSegments);
        final int initialGeneLength = geneSegments.size();
        int randomIndex = randomGenerator.nextInt(geneSegments.size());

        assert initialGeneLength == 30 : "geneSegments Size does not match guidelines";
        System.out.println("geneSegments Size: " + initialGeneLength);

        //this.geneSegments.clear();

        Gene temp = myGenes.get(randomIndex);                       // temp to store gene
        myGenes.remove(randomIndex);                                // remove the gene (stored in temp)
        randomIndex = randomGenerator.nextInt(geneSegments.size()); // new random index
        myGenes.add(randomIndex, temp);                             // add gene back from temp

    }
}