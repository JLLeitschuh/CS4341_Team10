package edu.wpi.cs4341.ga;

import java.util.Collections;
import java.util.List;

public abstract class AbstractPuzzle<T> {

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
     * Return the individual genes of the initial population.
     * @return
     */
    public abstract List<AbstractIndividual> getIndividuals();

}
