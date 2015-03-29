package org.forje.gurps;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Brian on 3/29/15.
 */
public class RandomizerFactoryTest {

    @Test
    public void testSimpleWithinRange() throws Exception {



        final Randomizer randomizer = RandomizerFactory.getRandomizer(6);

        assertMinMax(1, 6, randomizer);

    }

    private static void assertMinMax(final int expectedMin,
                                     final int expectedMax,
                                     final Randomizer randomizer) {

        int min = expectedMin;
        int max = expectedMax;

        final long start = System.currentTimeMillis();
        long current = 0;

        do {

            int i = randomizer.produceValue();

            if (i > max) {
                max = i;
            }
            if (i < min) {
                min = i;
            }

            current = System.currentTimeMillis();

        } while (current - start < 1000);

        Assert.assertEquals("min", expectedMin, min);
        Assert.assertEquals("max", expectedMax, max);

    }

    @Test
    public void testFromStringValidatesFormat() throws Exception {

        try {
            Randomizer r = RandomizerFactory.fromString("blah");
            Assert.fail("Expected an exception.");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("[blah] is not a valid string.", e.getMessage());
        }

    }

    @Test
    public void test2d6ToString() throws Exception {
        final Randomizer randomizer = RandomizerFactory.fromString("2d6");
        Assert.assertEquals("2d6", randomizer.toString());
    }

    @Test
    public void test2D6Min() throws Exception {
        final Randomizer randomizer = RandomizerFactory.fromString("2d6");
        Assert.assertEquals(2, randomizer.getMin());
    }

    @Test
    public void test2D6Range() throws Exception {
        final Randomizer randomizer = RandomizerFactory.fromString("2d6");
        assertMinMax(2,12,randomizer);
    }

    @Test
    public void test2D6Max() throws Exception {
        final Randomizer randomizer = RandomizerFactory.fromString("2d6");
        Assert.assertEquals(12, randomizer.getMax());
    }

    @Test
    public void testFromStringCountZeroInvalid() throws Exception {
        try {
            final Randomizer randomizer = RandomizerFactory.fromString("0d6");
            Assert.fail("Expected an exception.");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("[0d6] is not a valid string.", e.getMessage());
        }
    }

    @Test
    public void testFromStringSidesZeroInvalid() throws Exception {
        try {
            final Randomizer randomizer = RandomizerFactory.fromString("2d0");
            Assert.fail("Expected an exception.");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("[2d0] is not a valid string.", e.getMessage());
        }
    }

}
