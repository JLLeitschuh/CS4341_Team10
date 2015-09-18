package edu.wpi.cs4341.puzzle1;

import edu.wpi.cs4341.ga.AbstractIndividual;
import edu.wpi.cs4341.ga.Gene;

import java.util.Iterator;
import java.util.LinkedHashSet;

public class PackedIndividual extends AbstractIndividual {
    private final Integer targetValue;
    public PackedIndividual(LinkedHashSet<Gene> geneSegments, Integer targetValue){
        super(geneSegments);
        this.targetValue = targetValue;
    }

    @Override
    public float getFitness() {
        int currentFitness = 0;
        Iterator<Gene> itr = geneSegments.iterator();
        while(itr.hasNext()){
            Gene g = itr.next();
            currentFitness += (Integer) g.get();
        }
        if (currentFitness <= targetValue) {
            currentFitness = targetValue - currentFitness;
        }
        else {
            currentFitness = 0;
        }
        return currentFitness;
    }
}
