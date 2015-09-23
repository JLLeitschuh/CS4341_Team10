package edu.wpi.cs4341.puzzle3;

import edu.wpi.cs4341.ga.AbstractIndividual;
import edu.wpi.cs4341.ga.Gene;

import java.util.LinkedHashSet;

public class TowerIndividual extends AbstractIndividual {

    public TowerIndividual(LinkedHashSet<Gene> towerSegments){
        super(towerSegments);
    }

    @Override
    public AbstractIndividual crossOver(AbstractIndividual otherIndividual) {
        return new TowerIndividual(singlePointCrossover(otherIndividual));
    }

    @Override
    public float getFitness() {
        int currentFitness = 0;
        int height = 0;
        int cost = 0;
        for (Gene<TowerSegment> g: geneSegments){
            height++;
            cost+= g.get().getCost();
        }
        if (checkValidTower()){
            currentFitness=10+height-cost;
        } else currentFitness = 0;

        return currentFitness;
    }

<<<<<<< HEAD
    private boolean checkValidTower(){
        return true;
    }

=======
    @Override
    public AbstractIndividual copy() {
        return null;
    }
>>>>>>> 249ed3fbe26cc952a440f6b94bdb68942b433804
}
