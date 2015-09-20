package edu.wpi.cs4341.puzzle1;

import edu.wpi.cs4341.ga.Gene;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedHashSet;

import static org.junit.Assert.assertEquals;

public class PackedIndividualTest {
    private LinkedHashSet hashSet;

    @Before
    public void setUp(){
        hashSet = new LinkedHashSet();
        hashSet.add(new Gene<>(3));
        hashSet.add(new Gene<>(4));
        hashSet.add(new Gene<>(3));
    }

    @Test
    public void testGetFitnessNormal(){
        PackedIndividual individual = new PackedIndividual(hashSet, 11);
        assertEquals("The fitness was not correct", 10, (int)individual.getFitness());
    }

    @Test
    public void testGetFitnessTooHigh(){
        PackedIndividual individual = new PackedIndividual(hashSet, 3);
        assertEquals("The fitness should have been zero", 0, (int)individual.getFitness());
    }
}