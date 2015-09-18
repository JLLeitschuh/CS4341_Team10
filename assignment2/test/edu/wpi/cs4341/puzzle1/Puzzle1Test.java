package edu.wpi.cs4341.puzzle1;

import edu.wpi.cs4341.ga.AbstractIndividual;
import edu.wpi.cs4341.ga.Gene;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

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

}