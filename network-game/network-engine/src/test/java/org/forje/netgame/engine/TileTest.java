package org.forje.netgame.engine;

import org.junit.Assert;
import org.junit.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class TileTest {

    @Test
    public void testInitialOrientation() throws Exception {

        final Tile tile = new Tile(PieceType.corner);
        Orientation orientation = tile.getOrientation();
        Assert.assertEquals(Orientation.Up, orientation);

    }

    @Test
    public void testRotateAffectsOrientation() throws Exception {
        final Tile tile = new Tile(PieceType.corner);
        tile.rotate();
        Orientation orientation = tile.getOrientation();
        Assert.assertEquals(Orientation.Right, orientation);
    }

    @org.junit.Test
    public void testGetExits() throws Exception {

        final Tile tile = new Tile(PieceType.corner);
        Orientation[] exits = tile.getExits();
        Assert.assertArrayEquals(new Orientation[]{Orientation.Up, Orientation.Right}, exits);

    }

    @Test
    public void testRotateClockwise() throws Exception {

        final Tile tile = new Tile(PieceType.corner);
        Assert.assertArrayEquals(new Orientation[] {Orientation.Up,Orientation.Right}, tile.getExits());
        tile.rotate();
        Assert.assertArrayEquals(new Orientation[]{Orientation.Right, Orientation.Down}, tile.getExits());

    }

    @Test
    public void testRotateClockwiseMod() throws Exception {

        final Tile tile = new Tile(PieceType.tee);

        Assert.assertArrayEquals(new Orientation[] {Orientation.Up,Orientation.Right,Orientation.Left}, tile.getExits());
        tile.rotate();
        Assert.assertArrayEquals(new Orientation[] {Orientation.Right,Orientation.Down,Orientation.Up}, tile.getExits());

    }

    @Test
    public void testEmitsPropertyChangeEventOnRotate() throws Exception {

        ChangeCounter counter = new ChangeCounter(Tile.TILE_ORIENTATION_PROPERTY_NAME);

        final Tile tile = new Tile(PieceType.tee);
        tile.addPropertyChangeListener(counter);
        Assert.assertEquals(0, counter.getCount());
        tile.rotate();
        Assert.assertEquals(1, counter.getCount());

    }

    @Test
    public void testNullPieceType() throws Exception {

        try {
            Tile tile = new Tile(null);
            Assert.fail("Expected an exception.");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testPropertyChangeEventBeforeAfterValues() throws Exception {

        ChangeCounter counter = new ChangeCounter("Tile.Orientation");

        final Tile tile = new Tile(PieceType.tee);
        final Orientation before = tile.getOrientation();

        tile.addPropertyChangeListener(counter);

        Assert.assertEquals(0, counter.getCount());

        tile.rotate();

        Assert.assertEquals(1, counter.getCount());

        final Orientation after = tile.getOrientation();

        Assert.assertEquals("old value", before, counter.getLastEvent().getOldValue());
        Assert.assertEquals("new value", after, counter.getLastEvent().getNewValue());

    }

    private static class ChangeCounter implements PropertyChangeListener {

        private final String _propertyName;
        private int _count = 0;
        private PropertyChangeEvent _lastEvent;

        public ChangeCounter(final String propertyName) {
            _propertyName = propertyName;
        }

        public void propertyChange(final PropertyChangeEvent evt) {
            if (_propertyName.equals(evt.getPropertyName())) {
                _count++;
                _lastEvent = evt;
            }
        }

        public int getCount() {
            return _count;
        }

        public PropertyChangeEvent getLastEvent() {
            return _lastEvent;
        }

    }

    @Test
    public void testRoateWithCount() throws Exception {
        final Tile tile = new Tile(PieceType.line);
        final Orientation before = tile.getOrientation();
        tile.rotate(2);
        final Orientation after = tile.getOrientation();
        Assert.assertEquals(before.getRotationsRequired(after), 2);
    }

}
