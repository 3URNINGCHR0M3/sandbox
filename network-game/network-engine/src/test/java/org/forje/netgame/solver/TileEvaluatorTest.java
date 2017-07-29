package org.forje.netgame.solver;

import org.forje.netgame.engine.Board;
import org.forje.netgame.engine.PieceType;
import org.forje.netgame.engine.Tile;
import org.forje.netgame.solver.TileEvaluator;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Brian on 7/21/15.
 */
public class TileEvaluatorTest {

    @Test
    public void testTwoEndsConnected() throws Exception {

        final Board board = new Board(1, 2);

        final Tile alpha = new Tile(PieceType.end);
        alpha.rotate();

        final Tile beta = new Tile(PieceType.end);
        beta.rotate();
        beta.rotate();
        beta.rotate();

        board.setTile(0, 0, alpha);
        board.setTile(0,1, beta);

        final TileEvaluator evaluator = new TileEvaluator(board);
        Assert.assertEquals(true, evaluator.check(0,0));
        Assert.assertEquals(true, evaluator.check(0,1));

    }

    @Test
    public void testTwoEndsNotConnected() throws Exception {

        final Board board = new Board(1, 2);
        final Tile alpha = new Tile(PieceType.end);
        alpha.rotate();
        final Tile beta = new Tile(PieceType.end);
        board.setTile(0, 0, alpha);
        board.setTile(0,1, beta);

        final TileEvaluator evaluator = new TileEvaluator(board);
        Assert.assertEquals(false, evaluator.check(0,0));
        Assert.assertEquals(false, evaluator.check(0,1));

    }

    @Test
    public void testColumnOutOfBoundCheck() throws Exception {

        final Board board = new Board(1, 2);
        final Tile alpha = new Tile(PieceType.end);

        final Tile beta = new Tile(PieceType.end);
        beta.rotate();

        board.setTile(0, 0, alpha);
        board.setTile(0,1, beta);

        final TileEvaluator evaluator = new TileEvaluator(board);
        Assert.assertEquals(false, evaluator.check(0,0));
        Assert.assertEquals(false, evaluator.check(0,1));
    }


}
