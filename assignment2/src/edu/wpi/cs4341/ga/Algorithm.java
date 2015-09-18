package edu.wpi.cs4341.ga;

public class Algorithm {
    private AbstractIndividual bestInvididual;

    public static Population evolvePopulation(Population population){

        AbstractIndividual bestInvididual = population.getIndividuals().get(0);
        for (int i = 0; i < population.getIndividuals().size(); i++) {
            if (population.getIndividuals().get(i).getFitness() > bestInvididual.getFitness()) {
                bestInvididual = population.getIndividuals().get(i);
            }
        }
        System.out.println("Best Individual Fitness: " + bestInvididual.getFitness());

        return population; //For now

    }




    public void mutate(AbstractIndividual individual){

    }

    public void crossOver(AbstractIndividual individualA, AbstractIndividual individualB){

    }
}
