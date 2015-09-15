package edu.wpi.cs4341.ga;

import java.util.Collections;
import java.util.List;

public abstract class AbstractPuzzle {

    /**
     * The list of the current genes.
     * @return
     */
    protected List<IGene> genePool;

    protected AbstractPuzzle(List<IGene> genePool) {
        this.genePool = genePool;
    }
    /**
     * Return the individual genes.
     * @return
     */
    List<IGene> getGenes() {
        return Collections.unmodifiableList(this.genePool);
    }


    /**
     * Return the individual genes of the initial population.
     * @return
     */
    public abstract List<AbstractIndividual> getIndividuals();

}
