package edu.wpi.cs4341.puzzle1;

import edu.wpi.cs4341.ga.AbstractIndividual;
import edu.wpi.cs4341.ga.Gene;

import java.util.LinkedHashSet;

public class PackedIndividual extends AbstractIndividual {

    public PackedIndividual(LinkedHashSet<Gene> geneSegments){
        super(geneSegments);
    }

    @Override
    public float getFitness() {
        return 0;
    }
}
