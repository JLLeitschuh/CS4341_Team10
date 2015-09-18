package edu.wpi.cs4341.ga;

import java.util.*;

public abstract class AbstractPuzzle<T> {
    private Random randomGenerator = new Random();

    /**
     * The list of the current genes.
     * @return
     */
    protected List<Gene<T>> genePool;

    protected AbstractPuzzle(List<Gene<T>> genePool) {
        this.genePool = genePool;
    }
    /**
     * Return the individual genes.
     * @return
     */
    public List<Gene<T>> getGenes() {
        return Collections.unmodifiableList(this.genePool);
    }

    /**
     * Generates a random sequence of genes from the gene pool.
     * If the function {@link getFixedGeneSequenceLength()} has been overridden to return something greater than -1
     * this function will generate a random gene sequence of that length. Otherwise it will be at max the length of the
     * gene pool.
     * @return A linked hash set with the new gene sequence
     */
    protected LinkedHashSet<Gene> getRandomGeneSequence(){
        final int minGeneLength = 1;
        assert (genePool.size() > minGeneLength) : "The gene pool length was less than " + minGeneLength;
        final int randomGeneSequenceLength = minGeneLength + randomGenerator.nextInt(genePool.size() - minGeneLength);
        /*
         * If the getFixedGeneSequenceLength() has been overridden then use it to define the length of the gene sequence
         * otherwise use a random gene sequence length.
         */
        final int geneSequenceLength = getFixedGeneSequenceLength() < 0 ? randomGeneSequenceLength : getFixedGeneSequenceLength();
        LinkedHashSet<Gene> geneSequence = new LinkedHashSet<>();
        // Fill until full
        while(geneSequence.size() < geneSequenceLength){
            // Linked hash set guarantees to not have duplicates.
            geneSequence.add(getRandomGene());
        }
        return geneSequence;
    }

    /**
     * Gets a random gene from the gene pool
     * @return A randomly selected gene
     */
    protected Gene getRandomGene(){
        return genePool.get(randomGenerator.nextInt(genePool.size()));
    }


    /**
     * Return the individual genes of the initial population.
     * @return
     */
    public abstract List<AbstractIndividual> getIndividuals();

    public int getFixedGeneSequenceLength(){
        return -1;
    }

    public abstract int getPopulationSize();

}
