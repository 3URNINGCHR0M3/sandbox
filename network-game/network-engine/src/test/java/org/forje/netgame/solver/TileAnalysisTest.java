package org.forje.netgame.solver;

import org.forje.netgame.engine.Orientation;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;

public class TileAnalysisTest {

    @Test
    public void testSetGetConfidence() throws Exception {
        final TileAnalysis tileAnalysis = new TileAnalysis();
        tileAnalysis.setConfidence(.75d);
        Assert.assertEquals(0.75, tileAnalysis.getConfidence(), 0.001d);
    }

    @Test
    public void testSetConfidenceUpperBound() throws Exception {
        final TileAnalysis tileAnalysis = new TileAnalysis();
        try {
            tileAnalysis.setConfidence(1.1d);
            fail("Expected an exception.");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Confidence may not be greater than 1.", e.getMessage());
        }
    }

    @Test
    public void testSetConfidenceLowerBound() throws Exception {
        final TileAnalysis tileAnalysis = new TileAnalysis();
        try {
            tileAnalysis.setConfidence(-0.9d);
            fail("Expected an exception.");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Confidence may not be less than 0.", e.getMessage());
        }
    }

    @Test
    public void testFullConfidenceSetsSolved() throws Exception {
        final TileAnalysis tileAnalysis = new TileAnalysis();
        tileAnalysis.setConfidence(1d);
        Assert.assertEquals(true, tileAnalysis.isSolved());
    }

    @Test
    public void testConfidenceDefaultsZero() throws Exception {
        final TileAnalysis tileAnalysis = new TileAnalysis();
        Assert.assertEquals(0, tileAnalysis.getConfidence(), 0.0d);
    }

    @Test
    public void testSolvedDefaultsFalse() throws Exception {
        final TileAnalysis tileAnalysis = new TileAnalysis();
        Assert.assertEquals(false, tileAnalysis.isSolved());
    }

    @Test
    public void testSetGetBlank() throws Exception {

        TileAnalysis tileAnalysis = new TileAnalysis();
        Assert.assertEquals(true, tileAnalysis.isBlank());
        tileAnalysis.setBlank(false);
        Assert.assertEquals(false, tileAnalysis.isBlank());

    }

    @Test
    public void testBlankDefaultsTrue() throws Exception {
        TileAnalysis tileAnalysis = new TileAnalysis();
        Assert.assertEquals(true, tileAnalysis.isBlank());
    }

    @Test
    public void testGetSetAvailableMoves() throws Exception {

        TileAnalysis tileAnalysis = new TileAnalysis();

        final List<Orientation> input = new ArrayList<Orientation>();
        input.add(Orientation.Down);
        input.add(Orientation.Right);
        tileAnalysis.setAvailableMoves(input);

        List<Orientation> output = tileAnalysis.getAvailableMoves();

        Assert.assertEquals(input.size(), output.size());
        Assert.assertEquals(input.get(0), output.get(0));
        Assert.assertEquals(input.get(1), output.get(1));

    }

    @Test
    public void testSetAvailableMovesMakesCopy() throws Exception {
        TileAnalysis tileAnalysis = new TileAnalysis();

        final List<Orientation> input = new ArrayList<Orientation>();
        input.add(Orientation.Down);
        input.add(Orientation.Right);
        tileAnalysis.setAvailableMoves(input);

        List<Orientation> output = tileAnalysis.getAvailableMoves();

        input.clear();

        Assert.assertEquals(2, output.size());
        Assert.assertEquals(Orientation.Down, output.get(0));
        Assert.assertEquals(Orientation.Right, output.get(1));
    }


    @Test
    public void testGetAvailableMovesMakesCopy() throws Exception {
        TileAnalysis tileAnalysis = new TileAnalysis();

        final List<Orientation> input = new ArrayList<Orientation>();
        input.add(Orientation.Down);
        input.add(Orientation.Right);
        tileAnalysis.setAvailableMoves(input);

        List<Orientation> alpha = tileAnalysis.getAvailableMoves();

        alpha.clear();
        alpha.add(Orientation.Up);

        List<Orientation> output = tileAnalysis.getAvailableMoves();

        Assert.assertEquals(2, output.size());
        Assert.assertEquals(Orientation.Down, output.get(0));
        Assert.assertEquals(Orientation.Right, output.get(1));
    }

}
