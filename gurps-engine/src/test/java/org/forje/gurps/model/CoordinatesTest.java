package org.forje.gurps.model;

import org.junit.Assert;
import org.junit.Test;

public class CoordinatesTest {

    @Test
    public void testConstructorGetters() throws Exception {
        final Coordinates coordinates = new Coordinates(1, 2, 3);
        Assert.assertEquals("x", 1, coordinates.getX());
        Assert.assertEquals("y", 2, coordinates.getY());
        Assert.assertEquals("z", 3, coordinates.getZ());
    }

    @Test
    public void testAddX() throws Exception {

        final Coordinates coordinates = new Coordinates(1, 2, 3);
        Coordinates newCoordinates = coordinates.move(1,0,0);
        Assert.assertEquals("x modified", 2, newCoordinates.getX());
        Assert.assertEquals("y unmodified", 2, newCoordinates.getY());
        Assert.assertEquals("z unmodified", 3, newCoordinates.getZ());

    }

    @Test
    public void testAddY() throws Exception {

        final Coordinates coordinates = new Coordinates(1, 2, 3);
        Coordinates newCoordinates = coordinates.move(0,10,0);
        Assert.assertEquals("x unmodified", 1, newCoordinates.getX());
        Assert.assertEquals("y modified", 12, newCoordinates.getY());
        Assert.assertEquals("z unmodified", 3, newCoordinates.getZ());

    }

    @Test
    public void testAddZ() throws Exception {

        final Coordinates coordinates = new Coordinates(1, 2, 3);
        Coordinates newCoordinates = coordinates.move(0,0,5);
        Assert.assertEquals("x unmodified", 1, newCoordinates.getX());
        Assert.assertEquals("y unmodified", 2, newCoordinates.getY());
        Assert.assertEquals("z modified", 8, newCoordinates.getZ());

    }

    @Test
    public void testMoveNegativeValues() throws Exception {
        final Coordinates coordinates = new Coordinates(6, 6, 6);
        Coordinates newCoordinates = coordinates.move(-5,-4,-3);
        Assert.assertEquals("x", 1, newCoordinates.getX());
        Assert.assertEquals("y", 2, newCoordinates.getY());
        Assert.assertEquals("z", 3, newCoordinates.getZ());
    }

    @Test
    public void testMoveNoSideEffect() throws Exception {
        final Coordinates coordinates = new Coordinates(6, 6, 6);
        Coordinates newCoordinates = coordinates.move(-5,-4,-3);
        Assert.assertEquals("x", 6, coordinates.getX());
        Assert.assertEquals("y", 6, coordinates.getY());
        Assert.assertEquals("z", 6, coordinates.getZ());
    }

    @Test
    public void testToString() throws Exception {
        Assert.fail("test not implemented");
    }

}
