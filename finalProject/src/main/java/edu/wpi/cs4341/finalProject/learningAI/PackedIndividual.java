package edu.wpi.cs4341.finalProject.learningAI;

import edu.wpi.cs4341.finalProject.ga.AbstractIndividual;
import edu.wpi.cs4341.finalProject.ga.Gene;

import java.util.LinkedHashSet;

public class PackedIndividual extends AbstractIndividual {
    private final Integer targetValue;

    /**
     * @param geneSegments The gene segments that make up his individual.
     * @param targetValue The value that we want to hit in our gene.
     */
    public PackedIndividual(LinkedHashSet<Gene> geneSegments, Integer targetValue){
        super(geneSegments);
        this.targetValue = targetValue;
    }

    /**
     * Copy constructor for PackedIndividual
     * @param individual The individual to copy
     */
    private PackedIndividual(PackedIndividual individual){
        super((LinkedHashSet<Gene>) individual.geneSegments.clone());
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
        return 0; //for now.
    }
}
