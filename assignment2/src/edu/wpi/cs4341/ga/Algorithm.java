package edu.wpi.cs4341.ga;

public class Algorithm {
    private AbstractIndividual bestInvididual;

    public static Population evolvePopulation(Population population){
        AbstractIndividual bestInvididual = population.getBestIndividual();
        //System.out.println("Best Individual Fitness: " + bestInvididual.getFitness());

        return population; //For now

    }




    public void mutate(AbstractIndividual individual){

    }

    public void crossOver(AbstractIndividual individualA, AbstractIndividual individualB){

    }
}
