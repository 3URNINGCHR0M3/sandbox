package org.forje.netgame.engine;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Brian on 7/21/15.
 */
public class BoardTest {


    @Test
    public void testConstructor() throws Exception {
        Board board = new Board(10,10);
    }

    @Test
    public void testGetRows() throws Exception {
        Board board = new Board(5,10);
        Assert.assertEquals(5, board.getRows());
    }

    @Test
    public void testGetColumns() throws Exception {
        Board board = new Board(10,2);
        Assert.assertEquals(2, board.getColumns());
    }

    @Test
    public void testSetAndGetTile() throws Exception {
        final Board board = new Board(3, 3);
        final Tile tile = new Tile(PieceType.end);
        board.setTile(1, 1, tile);
        Assert.assertSame(tile, board.getTile(1,1));
    }

    @Test
    public void testRotateTile() throws Exception {

        final Board board = new Board(3, 3);

        final Tile tile = new Tile(PieceType.tee);
        board.setTile(1,1,tile);

        Assert.assertArrayEquals(new Orientation[] {Orientation.Up,Orientation.Right,Orientation.Left}, tile.getExits());
        board.rotate(1,1);
        Assert.assertArrayEquals(new Orientation[]{Orientation.Right,Orientation.Down,Orientation.Up}, tile.getExits());

    }

}
