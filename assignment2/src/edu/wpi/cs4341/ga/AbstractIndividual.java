package edu.wpi.cs4341.ga;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;

/**
 * Defines an abstract implementation of an individual.
 * An individual is made up of a combination of genes from the initial gene pool.
 */
public abstract class AbstractIndividual {
    protected final Random randomGenerator = new Random();
    /**
     * List of the gene segments that define this individual
     */
    protected LinkedHashSet<Gene> geneSegments;

    /**
     * Creates an abstract individual.
     * @param geneSegments The gene segments that make up this gene.
     */
    protected AbstractIndividual(LinkedHashSet<Gene> geneSegments){
        this.geneSegments = geneSegments;
    }

    /**
     * Allows external objects to look at the genes in the individual
     * @return An unmodifiable list of geneSegments that make up this gene.
     */
    public LinkedHashSet<Gene> getGeneSegments(){
        return geneSegments; // TODO: Return unmodifiable set
    }

    /**
     * Crosses two Abstract Individuals in order to create children individuals
     * @param otherIndividual The other individual to merge with
     * @return The child of this merging
     */
    public abstract AbstractIndividual crossOver(AbstractIndividual otherIndividual);

    protected LinkedHashSet<Gene> singlePointCrossover(AbstractIndividual otherIndividual){
        final List<Gene> myGenes = new ArrayList<>(geneSegments);
        final List<Gene> theirGenes = new ArrayList<>(otherIndividual.geneSegments);
        final List<Gene> newGene = new ArrayList<>();
        int splitIndex = randomGenerator.nextInt(Math.min(myGenes.size(), theirGenes.size()));

        newGene.addAll(myGenes.subList(0, splitIndex));
        newGene.addAll(theirGenes.subList(splitIndex, theirGenes.size()));

        return new LinkedHashSet<>(newGene);
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

    @Override
    public String toString() {
        return "AbstractIndividual{" +
                "geneSegments=" + geneSegments +
                '}';
    }
}
