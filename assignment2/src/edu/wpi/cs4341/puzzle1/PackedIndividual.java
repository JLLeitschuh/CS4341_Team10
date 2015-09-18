package edu.wpi.cs4341.puzzle1;

import edu.wpi.cs4341.ga.AbstractIndividual;
import edu.wpi.cs4341.ga.Gene;

import java.util.List;

public class PackedIndividual extends AbstractIndividual {

    public PackedIndividual(List<Gene> geneSegments){
        super(geneSegments);
    }

    @Override
    public float getFitness() {
        return 0;
    }
}
