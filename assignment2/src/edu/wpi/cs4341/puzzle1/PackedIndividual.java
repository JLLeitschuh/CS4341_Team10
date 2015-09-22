package edu.wpi.cs4341.puzzle1;

import edu.wpi.cs4341.ga.AbstractIndividual;
import edu.wpi.cs4341.ga.Gene;

import java.util.LinkedHashSet;

public class PackedIndividual extends AbstractIndividual {
    private final Integer targetValue;
    public PackedIndividual(LinkedHashSet<Gene> geneSegments, Integer targetValue){
        super(geneSegments);
        this.targetValue = targetValue;
    }

    private PackedIndividual(PackedIndividual individual){
        super(individual.geneSegments);
        this.targetValue = individual.targetValue;
    }

    @Override
    public AbstractIndividual crossOver(AbstractIndividual otherIndividual) {
        return new PackedIndividual(singlePointCrossover(otherIndividual), targetValue);
    }

    @Override
    public PackedIndividual copy(){
        return new PackedIndividual(this);
    }

    @Override
    public float getFitness() {
        int currentFitness = 0;
        for ( Gene g : geneSegments) {
            currentFitness += (Integer) g.get();
        }
        if (targetValue < currentFitness ) {
            currentFitness = 0;
        }
        return currentFitness;
    }
}
