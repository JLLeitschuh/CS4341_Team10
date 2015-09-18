package edu.wpi.cs4341.ga;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Population {
    private final List<AbstractIndividual> abstractIndividuals;
    private final AbstractIndividual bestIndividual;
    private final int generationNumber;

    public Population(List<AbstractIndividual> abstractIndividuals, int generationNumber){
        this.abstractIndividuals = abstractIndividuals;
        this.generationNumber = generationNumber;
        //Sort in order of best individual first
        this.abstractIndividuals.sort((m, n) -> Float.compare(n.getFitness(), m.getFitness()));
        this.bestIndividual = this.abstractIndividuals.get(0);
    }
    // TODO: Write unit test!
    private List<List<AbstractIndividual>> getIndividualRange (int initial, int ... range) {
        List<List<AbstractIndividual>> rangeList = new ArrayList<>();
        int startRange = initial;
        for (int i = 0; i < range.length; i++) {
            int endRange = range[i];
            rangeList.add(new ArrayList<AbstractIndividual>(abstractIndividuals.subList(startRange/100, endRange/100)));
            startRange = range[i];
        }
        return rangeList;
    }

    public List<AbstractIndividual> getIndividuals() {
        return this.abstractIndividuals;
    }

    public AbstractIndividual getBestIndividual(){
        return bestIndividual;
    }
}
