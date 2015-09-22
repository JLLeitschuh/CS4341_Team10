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
     * Should call the copy constructor for this object
     * @return A copy of this individual. There is an infered contract that this
     */
    public abstract AbstractIndividual copy();

    /**
     * Mutates the Individual changing its internal gene structure
     */
    public void mutate(AbstractPuzzle puzzleRules){
        List<Gene> myGenes = new ArrayList<>(this.geneSegments);
        this.geneSegments.clear();
        System.out.println("geneSegments Size: " + geneSegments.size());
        final int randomIndex = randomGenerator.nextInt(geneSegments.size()); //TODO: Clearing the gene segments will result in zero size.
        // If we have a fixed gene size requirement
        if (puzzleRules.getFixedGeneSequenceLength() != AbstractPuzzle.NO_FIXED_GENE_SEQUENCE_LENGTH){
            final int initialGeneLength = geneSegments.size();
            myGenes.remove(randomIndex);
            Gene replacementGene;
            // Find a gene to put in here
            do {
                replacementGene = puzzleRules.getRandomGene();
            } while (myGenes.contains(replacementGene));
            myGenes.add(replacementGene);
            this.geneSegments.addAll(myGenes);
            assert this.geneSegments.size() == initialGeneLength : "The gene segments came out of mutate a different size than their initial size";
        } else {
            final int randomCase = randomGenerator.nextInt(3);
            switch (randomCase){
                case 0:
                    myGenes.remove(randomIndex);
                    break;
                case 1:
                    myGenes.add(randomIndex, puzzleRules.getRandomGene());
                    break;
                case 2:
                    myGenes.remove(randomIndex);
                    myGenes.add(randomIndex, puzzleRules.getRandomGene());
                    break;
                default:
                    assert false : "Invalid state";
                    break;
            }
            this.geneSegments.addAll(myGenes);
        }
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
