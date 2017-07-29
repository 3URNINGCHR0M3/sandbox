package org.forje.netgame.util;

import org.forje.netgame.engine.Board;
import org.forje.netgame.engine.PieceType;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.fail;

/**
 * Created by Brian on 9/13/15.
 */
public class StringBoardBuilderTest {

    @Test
    public void testFourCorners() throws Exception {

        String input = "CC,CC,CC";
        Board board = StringBoardBuilder.buildBoard(input);

        Assert.assertEquals(3, board.getRows());
        Assert.assertEquals(2, board.getColumns());
        Assert.assertEquals(PieceType.corner, board.getTile(0,0).getType() );
        Assert.assertEquals(PieceType.corner, board.getTile(0,1).getType() );
        Assert.assertEquals(PieceType.corner, board.getTile(1,0).getType() );
        Assert.assertEquals(PieceType.corner, board.getTile(1,1).getType());

    }

    @Test
    public void testHandleSpaces() throws Exception {

        String input = "CLC,L L,CLC";
        Board board = StringBoardBuilder.buildBoard(input);

        Assert.assertEquals(3, board.getRows());
        Assert.assertEquals(3, board.getColumns());
        Assert.assertEquals(PieceType.corner, board.getTile(0,0).getType() );
        Assert.assertEquals(PieceType.line, board.getTile(0,1).getType() );
        Assert.assertEquals(PieceType.corner, board.getTile(0,2).getType() );
        Assert.assertEquals(PieceType.line, board.getTile(1,0).getType());
        Assert.assertNull(board.getTile(1, 1));
        Assert.assertEquals(PieceType.line, board.getTile(1,2).getType());
        Assert.assertEquals(PieceType.corner, board.getTile(2,0).getType());
        Assert.assertEquals(PieceType.line, board.getTile(2,1).getType());
        Assert.assertEquals(PieceType.corner, board.getTile(2,2).getType());
    }

    @Test
    public void testAcceptValidCharacters() throws Exception {
        String input = "CLE,T P";

        Board board = StringBoardBuilder.buildBoard(input);

        Assert.assertEquals(2, board.getRows());
        Assert.assertEquals(3, board.getColumns());

        Assert.assertEquals(PieceType.corner, board.getTile(0,0).getType() );
        Assert.assertEquals(PieceType.line, board.getTile(0,1).getType() );
        Assert.assertEquals(PieceType.end, board.getTile(0,2).getType() );

        Assert.assertEquals(PieceType.tee, board.getTile(1,0).getType());
        Assert.assertNull(board.getTile(1, 1));
        Assert.assertEquals(PieceType.plus, board.getTile(1,2).getType());

    }

    @Test
    public void testRejectInvalidCharacters() throws Exception {

        String input = "CLE,TQP";

        try {
            Board board = StringBoardBuilder.buildBoard(input);
            Assert.fail("Expected an exception.");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Unsupported character [Q] at [2,2].", e.getMessage());
        }

    }

    @Test
    public void testValidateColsConsistent() throws Exception {

        String input = "CCE,CC";

        try {
            Board board = StringBoardBuilder.buildBoard(input);
            fail("Expected an exception.");
        } catch (IllegalArgumentException iae) {
            Assert.assertEquals("Number of columns in row [2] is inconsistent.", iae.getMessage());
        }

    }



}
