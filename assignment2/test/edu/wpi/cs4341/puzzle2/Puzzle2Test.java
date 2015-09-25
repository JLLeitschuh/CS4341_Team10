package edu.wpi.cs4341.puzzle2;

import edu.wpi.cs4341.Puzzle2.BinsIndividual;
import edu.wpi.cs4341.Puzzle2.Puzzle2;
import edu.wpi.cs4341.ga.Gene;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class Puzzle2Test {
    private Puzzle2 puzzle2;

    @Before
    public void setUp() {
        puzzle2 = new Puzzle2(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"));
    }

    @Test
    public void testGetGenePool() {
//        List<Gene<Float>> geneList = puzzle2.getGenes();
//        assertEquals("The size of the gene list is incorrect.", 30, geneList.size());
    }

    @Test
    public void testBinsIndividual() {
        LinkedHashSet<Gene> testSet = puzzle2.getRandomGeneSequence();
        BinsIndividual binsIndividual = new BinsIndividual(new LinkedHashSet(testSet));
        binsIndividual.mutate(puzzle2);
        testSet.forEach(System.out::print);
        System.out.println();
        binsIndividual.getGeneSegments().forEach(System.out::print);
        System.out.println();
        assertThat("Gene segments were in exactly the same order as before mutate.", binsIndividual.getGeneSegments(), not(contains(testSet)));
        //assertThat("Gene segments after mutate did not contain all of the genes that the initial set had.", binsIndividual.getGeneSegments(), hasItems(testSet.toArray()));
    }



}
