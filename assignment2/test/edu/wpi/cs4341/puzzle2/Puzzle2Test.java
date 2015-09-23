package edu.wpi.cs4341.puzzle2;

import edu.wpi.cs4341.Puzzle2.Puzzle2;
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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Puzzle2Test {
    private Puzzle2 puzzle2;

    @Before
    public void setUp() {
        puzzle2 = new Puzzle2(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"));
    }

    @Test
    public void testGetGenePool() {
        List<Gene<Float>> geneList = puzzle2.getGenes();
        assertEquals("The size of the gene list is incorrect.", 30, geneList.size());
    }

    

}
