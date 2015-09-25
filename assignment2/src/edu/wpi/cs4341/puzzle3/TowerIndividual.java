package edu.wpi.cs4341.puzzle3;

import edu.wpi.cs4341.ga.AbstractIndividual;
import edu.wpi.cs4341.ga.Gene;

import java.util.*;

public class TowerIndividual extends AbstractIndividual {

    public TowerIndividual(LinkedHashSet<Gene> towerSegments){
        super(towerSegments);
    }

    private TowerIndividual(TowerIndividual copyMe){
        super(new LinkedHashSet(copyMe.geneSegments));
    }


    @Override
    public AbstractIndividual crossOver(AbstractIndividual otherIndividual) {
        return new TowerIndividual(singlePointCrossover(otherIndividual));
    }

    @Override
    public float getFitness() {
        int cost = 0;
        for (Gene<TowerSegment> g: geneSegments){
            cost+= g.get().getCost();
        }
        if (checkValidTower()){
            return 10 + (geneSegments.size() * geneSegments.size()) - cost;
        } else return 0;

    }

    private boolean checkValidTower() {
        LinkedList<Gene<TowerSegment>> towerList = new LinkedList(geneSegments);

        if( towerList.size() < 2){
            return false;
        }

        // Validate that the widths are okay.
        Iterator<Gene> towerElements = this.geneSegments.iterator();
        TowerSegment previousElement = (TowerSegment)towerElements.next().get();
        while(towerElements.hasNext()){
            TowerSegment currentElement = (TowerSegment)towerElements.next().get();
            if(!previousElement.canSupportOnTop(currentElement)){
                return false;
            }
            previousElement = currentElement;
        }
        // Widths are okay


        // Determine if strength is valid
        ListIterator<Gene<TowerSegment>> listIterator = towerList.listIterator(towerList.size());
        int height = 0;
        while (listIterator.hasPrevious()){
            TowerSegment currentSegment = listIterator.previous().get();
            if(!currentSegment.canSupportOnTop(height)) return false;
            height ++;
        }
        // The strength is valid


        // Determine that the segments are in order correctly.

        // First element is the base
        if(!towerList.poll().get().isVaidBase()){
            return false;
        }

        // Get the last element
        Gene<TowerSegment> lastElement = towerList.getLast();
        if(!lastElement.get().isValidTop()){
            return false;
        }
        towerList.remove(lastElement);

        while(!towerList.isEmpty()){
            if(!towerList.poll().get().isValidMiddle()){
                return false;
            }
        }

        return true;
    }

    @Override
    public TowerIndividual copy() {
        return new TowerIndividual(this);
    }
}
