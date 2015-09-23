package edu.wpi.cs4341.puzzle3;

import edu.wpi.cs4341.ga.AbstractIndividual;
import edu.wpi.cs4341.ga.Gene;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

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

    private boolean checkValidTower() {
        boolean isValid = true; //Valid otherwise stated.
        List<Gene> towerList = new ArrayList<Gene>(geneSegments);
        //This works. I'm not proud of it, but it works.
        TowerSegment firstSegment = ((Gene<TowerSegment>) towerList.get(0)).get();
        TowerSegment lastSegment = ((Gene<TowerSegment>) towerList.get(towerList.size() -1)).get();
        if((firstSegment.getSegmentType() != "Door") && (lastSegment.getSegmentType() != "Lookout")) {
//            System.out.println("");
            isValid = false;
            return isValid;
        }
        
        return isValid;
    }

    @Override
    public AbstractIndividual copy() {
        return null;
    }
}
