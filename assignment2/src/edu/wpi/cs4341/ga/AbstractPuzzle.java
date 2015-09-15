package edu.wpi.cs4341.ga;

import java.util.List;

public abstract class AbstractPuzzle {

    AbstractPuzzle(List<IGene> IGene) {}

    abstract List<IGene> genePool();
    abstract List<IGene> getIndividuals();
    abstract List<IGene> getGenes();

}
