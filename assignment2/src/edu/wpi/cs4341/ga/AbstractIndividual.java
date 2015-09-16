package edu.wpi.cs4341.ga;

import java.util.Collections;
import java.util.List;

/**
 * Defines an abstract implementation of an individual.
 * An individual is made up of a combination of genes from the initial gene pool.
 */
public abstract class AbstractIndividual {
    /**
     * List of the gene segments that define this individual
     */
    protected List<Gene> geneSegments;

    /**
     * Creates an abstract individual.
     * @param geneSegments The gene segments that make up this gene.
     */
    AbstractIndividual(List<Gene> geneSegments){
        this.geneSegments = geneSegments;
    }

    /**
     * Allows external objects to look at the genes in the individual
     * @return An unmodifiable list of geneSegments that make up this gene.
     */
    public List<Gene> getGeneSegments(){
        return Collections.unmodifiableList(geneSegments);
    }

    /**
     * Crosses two Abstract Individuals in order to create children individuals
     * @param otherIndividual The other individual to merge with
     * @return The children of this merging
     */
    public List<AbstractIndividual> crossOver(AbstractIndividual otherIndividual){
        assert false;
        return Collections.emptyList();
    }

    /**
     * Mutates the Individual changing its internal gene structure
     */
    public void mutate(){
    }

    /**
     * Gets the fitness of this individual.
     * @return A number that allows the fitness of this individual to be compared to other individuals.
     */
    public abstract float getFitness();
}
