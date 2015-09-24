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
        //System.out.println("CROSSOVER::Gene Segments Size: " + geneSegments.size());
        List<Gene> newGene = new ArrayList(singlePointCrossover(otherIndividual));
        List<Gene> myGeneCopy = new ArrayList<>(this.geneSegments);
        myGeneCopy.removeAll(newGene);
        newGene.addAll(randomGenerator.nextInt(newGene.size()), myGeneCopy);

        return new BinsIndividual(new LinkedHashSet(newGene));
    }

    @Override
    public BinsIndividual copy(){
        return null;
    }

    @Override
    public float getFitness() {
        float currentFitness = 0;
        List<Gene<Float>> myGenes = new ArrayList(this.geneSegments);
        //System.out.println("SIZE: " + myGenes.size());
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

        //System.out.println("MUTATE::Gene Segments Size: " + geneSegments.size());

        Random randomGenerator = new Random();
        List<Gene> myGenes = new ArrayList<>(this.geneSegments);
        final int initialGeneLength = geneSegments.size();
        int randomIndex = randomGenerator.nextInt(geneSegments.size());

        //System.out.println("geneSegments Size: " + initialGeneLength);
        assert initialGeneLength == 30 : "geneSegments Size does not match guidelines";

        this.geneSegments.clear();

        Gene temp = myGenes.get(randomIndex);                       // temp to store gene
        myGenes.remove(randomIndex);                                // remove the gene (stored in temp)
        randomIndex = randomGenerator.nextInt(myGenes.size()); // new random index
        myGenes.add(randomIndex, temp);                             // add gene back from temp

        this.geneSegments.addAll(myGenes);
        assert this.geneSegments.size() > 0 : "The gene sequence was shorter than 1";


    }
}
