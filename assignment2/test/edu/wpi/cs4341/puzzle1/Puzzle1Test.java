package edu.wpi.cs4341.puzzle1;

import edu.wpi.cs4341.ga.AbstractIndividual;
import edu.wpi.cs4341.ga.Gene;
import edu.wpi.cs4341.ga.Population;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;

public class Puzzle1Test {
    private Puzzle1 puzzle1;

    @Before
    public void setUp(){
        puzzle1 = new Puzzle1(Arrays.asList("11","2","3","5","7"));
    }

    @Test
    public void testGetGenePool(){
        List<Gene<Integer>> geneList = puzzle1.getGenes();
        assertEquals("The size of the gene list is incorrect.", 4, geneList.size());
        assertEquals("The fist element in the gene sequence was not correct.", new Integer(2), geneList.get(0).get());
        assertEquals("The last element in the gene sequence was not correct.", new Integer(7), geneList.get(3).get());


    }

    @Test
    public void testGetIndiviuals() {
        List<AbstractIndividual> abstractIndividuals = puzzle1.getIndividuals();
        assertEquals("The size of the indicidual list does not match the fixed population size",abstractIndividuals.size(), puzzle1.getPopulationSize());
    }


    public boolean noDuplicates() {
        return false;
    }

    @Test
    public void testGetIndividualRange() {
        Population currentPopulation = new Population(puzzle1.getIndividuals(), 0);
        List<List<AbstractIndividual>> newRange = currentPopulation.getIndividualRange(0, 20, 40, 90);


        for (List<AbstractIndividual> abstractIndividuals : newRange) {
            for (List<AbstractIndividual> abstractIndividualsInner : newRange) {
                //If both are the same list then they will have the same elements
                if (abstractIndividuals.equals(abstractIndividualsInner)) continue;
                for (AbstractIndividual abstractIndividual : abstractIndividuals) {
                    System.out.print(abstractIndividual.getFitness() + ", ");
                    assertThat(abstractIndividualsInner, not(hasItem(abstractIndividual)));
                }
            }
            System.out.println();
        }
        /*
        for (int i = 0; i < 3; i++) {
            for (int q = 0; q < 3; q++) {
                System.out.print(newRange.get(i).get(q).getFitness() + ", ");

                for (int j = 0; j < 3; j++) {
                    for (int g = 0; g < 3; g++) {
                        if (i != j) {
                            assertFalse(newRange.get(i).get(q) == newRange.get(j).get(q));
                        }
                    }
                }


            }

        }
        */


    }

}