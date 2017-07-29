package org.forje.netgame.engine;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Brian on 7/21/15.
 */
public class OrientationTest {

    @Test
    public void testRotate() throws Exception {

        Assert.assertEquals(Orientation.Right, Orientation.Up.rotate());
        Assert.assertEquals(Orientation.Down, Orientation.Right.rotate());
        Assert.assertEquals(Orientation.Left, Orientation.Down.rotate());
        Assert.assertEquals(Orientation.Up, Orientation.Left.rotate());

    }

    @Test
    public void testGetOpposing() throws Exception {

        Assert.assertEquals(Orientation.Down, Orientation.Up.getOpposing());
        Assert.assertEquals(Orientation.Left, Orientation.Right.getOpposing());
        Assert.assertEquals(Orientation.Up, Orientation.Down.getOpposing());
        Assert.assertEquals(Orientation.Right, Orientation.Left.getOpposing());
    }

    @Test
    public void testGetRotationsRequiredZero() throws Exception {
        Assert.assertEquals(0, Orientation.Down.getRotationsRequired(Orientation.Down));
        Assert.assertEquals(0, Orientation.Up.getRotationsRequired(Orientation.Up));
        Assert.assertEquals(0, Orientation.Left.getRotationsRequired(Orientation.Left));
        Assert.assertEquals(0, Orientation.Right.getRotationsRequired(Orientation.Right));
    }

    @Test
    public void testGetRotationsRequiredOne() throws Exception {
        Assert.assertEquals(1, Orientation.Up.getRotationsRequired(Orientation.Right));
        Assert.assertEquals(1, Orientation.Right.getRotationsRequired(Orientation.Down));
        Assert.assertEquals(1, Orientation.Down.getRotationsRequired(Orientation.Left));
        Assert.assertEquals(1, Orientation.Left.getRotationsRequired(Orientation.Up));
    }

    @Test
    public void testGetRotationsRequiredTwo() throws Exception {
        Assert.assertEquals(2, Orientation.Down.getRotationsRequired(Orientation.Down.getOpposing()));
        Assert.assertEquals(2, Orientation.Up.getRotationsRequired(Orientation.Up.getOpposing()));
        Assert.assertEquals(2, Orientation.Left.getRotationsRequired(Orientation.Left.getOpposing()));
        Assert.assertEquals(2, Orientation.Right.getRotationsRequired(Orientation.Right.getOpposing()));
    }


}
