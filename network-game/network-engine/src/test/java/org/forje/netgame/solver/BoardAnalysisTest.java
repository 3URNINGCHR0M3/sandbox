package org.forje.netgame.solver;

import org.forje.netgame.engine.Board;
import org.forje.netgame.engine.PieceType;
import org.forje.netgame.engine.Tile;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Brian on 10/6/15.
 */
public class BoardAnalysisTest {

    @Test
    public void testCreatesCorrectSize() throws Exception {

//        final Board board = new Board(2,2);
//        board.setTile(0,0, new Tile(PieceType.corner));
//        board.setTile(0,1, new Tile(PieceType.end));
//        board.setTile(1,0, new Tile(PieceType.line));
//        board.setTile(1, 1, new Tile(PieceType.tee));

        BoardAnalysis boardAnalysis = new BoardAnalysis(2,2);
        boardAnalysis.initialize();

        Assert.assertNotNull(boardAnalysis);

        Assert.assertNotNull(boardAnalysis.getTile(0,0));
        Assert.assertNotNull(boardAnalysis.getTile(0,1));
        Assert.assertNotNull(boardAnalysis.getTile(1,0));
        Assert.assertNotNull(boardAnalysis.getTile(1,1));

    }

    @Test
    public void testCantCallInitializeTwice() throws Exception {

        try {
            BoardAnalysis boardAnalysis = new BoardAnalysis(2,2);
            boardAnalysis.initialize();
            boardAnalysis.initialize();
            Assert.fail("Expected an exception.");
        } catch (IllegalStateException e) {

        }

    }

    @Test
    public void testImplementsBoardChangedListener() throws Exception {
        Assert.fail("test not implemented");
    }

    @Test
    public void testReturnsListOfAvailableMoves() throws Exception {
        // returns the list of available moves for a give tile (r,c)
        Assert.fail("test not implemented");
    }

    @Test
    public void testReturnsListOfAdjacent() throws Exception {
        Assert.fail("test not implemented");
    }



}
