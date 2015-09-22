package edu.wpi.cs4341.puzzle3;

import edu.wpi.cs4341.ga.AbstractIndividual;
import edu.wpi.cs4341.ga.Gene;

import java.util.LinkedHashSet;

/**
 * Created by nhtranngoc on 9/21/15.
 */
public class TowerIndividual extends AbstractIndividual {

    public TowerIndividual(LinkedHashSet<Gene> geneSegments){
        super(geneSegments);
    }

    @Override
    public AbstractIndividual crossOver(AbstractIndividual otherIndividual) {
        return new TowerIndividual(singlePointCrossover(otherIndividual));
    }

    @Override
    public float getFitness() {
        int currentFitness = 0;
//        for ( Gene g : geneSegments) {
//            currentFitness += (Integer) g.get();
//        }
//        if (targetValue < currentFitness ) {
//            currentFitness = 0;
//        }
        return currentFitness;
    }

    @Override
    public AbstractIndividual copy() {
        return null;
    }
}
