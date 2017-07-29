package org.forje.netgame.solver.strategies;

import org.forje.netgame.engine.Board;
import org.forje.netgame.engine.Orientation;
import org.forje.netgame.util.StringBoardBuilder;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Brian on 9/13/15.
 */
public class PerimeterStrategyTest {


    @Test
    public void testDoesntChangeCorrectLineOrientation() throws Exception {

        String input = "CLC,LPL,CLC";

        Board board = StringBoardBuilder.buildBoard(input);
        board.rotate(0,1);
        board.rotate(2, 1);

        Assert.assertEquals(Orientation.Up, board.getTile(0, 0).getOrientation());
        Assert.assertEquals(Orientation.Right, board.getTile(0, 1).getOrientation());
        Assert.assertEquals(Orientation.Up, board.getTile(0, 2).getOrientation());
        Assert.assertEquals(Orientation.Up, board.getTile(1, 0).getOrientation());
        Assert.assertEquals(Orientation.Up, board.getTile(1, 1).getOrientation());
        Assert.assertEquals(Orientation.Up, board.getTile(1, 2).getOrientation());
        Assert.assertEquals(Orientation.Up, board.getTile(2, 0).getOrientation());
        Assert.assertEquals(Orientation.Right, board.getTile(2, 1).getOrientation());
        Assert.assertEquals(Orientation.Up, board.getTile(2, 2).getOrientation());

        PerimeterStrategy strategy = new PerimeterStrategy();
        strategy.apply(board);

        Assert.assertEquals(Orientation.Up, board.getTile(0, 0).getOrientation());

        Assert.assertEquals(Orientation.Up, board.getTile(0, 2).getOrientation());
        Assert.assertEquals(Orientation.Up, board.getTile(1, 1).getOrientation());
        Assert.assertEquals(Orientation.Up, board.getTile(2, 0).getOrientation());
        Assert.assertEquals(Orientation.Up, board.getTile(2, 2).getOrientation());

    }

    @Test
    public void testCorrectsTopLine() throws Exception {

        String input = "CLLC,L  L,CLLC";

        Board board = StringBoardBuilder.buildBoard(input);
        board.rotate(0, 2);
        board.rotate(0, 2);


        Assert.assertEquals(Orientation.Up, board.getTile(0, 1).getOrientation());
        Assert.assertEquals(Orientation.Down, board.getTile(0, 2).getOrientation());

        PerimeterStrategy strategy = new PerimeterStrategy();
        strategy.apply(board);

        Assert.assertEquals(Orientation.Right, board.getTile(0, 1).getOrientation());
        Assert.assertEquals(Orientation.Left, board.getTile(0, 2).getOrientation());

    }


    @Test
    public void testCorrectsBottomLine() throws Exception {

        String input = "CLLC,L  L,CLLC";

        Board board = StringBoardBuilder.buildBoard(input);
        board.rotate(2, 2);
        board.rotate(2, 2);


        Assert.assertEquals(Orientation.Up, board.getTile(2, 1).getOrientation());
        Assert.assertEquals(Orientation.Down, board.getTile(2, 2).getOrientation());

        PerimeterStrategy strategy = new PerimeterStrategy();
        strategy.apply(board);

        Assert.assertEquals(Orientation.Right, board.getTile(2, 1).getOrientation());
        Assert.assertEquals(Orientation.Left, board.getTile(2, 2).getOrientation());
    }

    @Test
    public void testCorrectsRightEdgeLine() throws Exception {

        String input = "CLLC,L  L,L  L,CLLC";

        Board board = StringBoardBuilder.buildBoard(input);

        board.rotate(1,3);

        board.rotate(2,3);
        board.rotate(2,3);
        board.rotate(2,3);

        Assert.assertEquals(Orientation.Right, board.getTile(1, 3).getOrientation());
        Assert.assertEquals(Orientation.Left, board.getTile(2, 3).getOrientation());

        PerimeterStrategy strategy = new PerimeterStrategy();
        strategy.apply(board);

        Assert.assertEquals(Orientation.Down, board.getTile(1, 3).getOrientation());
        Assert.assertEquals(Orientation.Up, board.getTile(2, 3).getOrientation());

    }

    @Test
    public void testCorrectsLeftEdgeLine() throws Exception {

        String input = "CLLC,L  L,L  L,CLLC";

        Board board = StringBoardBuilder.buildBoard(input);

        board.rotate(1,0);

        board.rotate(2,0);
        board.rotate(2,0);
        board.rotate(2,0);

        Assert.assertEquals(Orientation.Right, board.getTile(1, 0).getOrientation());
        Assert.assertEquals(Orientation.Left, board.getTile(2, 0).getOrientation());

        PerimeterStrategy strategy = new PerimeterStrategy();
        strategy.apply(board);

        Assert.assertEquals(Orientation.Down, board.getTile(1, 0).getOrientation());
        Assert.assertEquals(Orientation.Up, board.getTile(2, 0).getOrientation());

    }

    @Test
    public void testTeeOnTop() throws Exception {

        final String input = "CTTTTC,CTTTTC";

        final Board board = StringBoardBuilder.buildBoard(input);

        board.rotate(0,2);

        board.rotate(0,3);
        board.rotate(0,3);

        board.rotate(0,4);
        board.rotate(0,4);
        board.rotate(0,4);

        Assert.assertEquals(Orientation.Up, board.getTile(0, 1).getOrientation());
        Assert.assertEquals(Orientation.Right, board.getTile(0, 2).getOrientation());
        Assert.assertEquals(Orientation.Down, board.getTile(0, 3).getOrientation());
        Assert.assertEquals(Orientation.Left, board.getTile(0, 4).getOrientation());

        PerimeterStrategy strategy = new PerimeterStrategy();
        strategy.apply(board);

        Assert.assertEquals(Orientation.Down, board.getTile(0, 1).getOrientation());
        Assert.assertEquals(Orientation.Down, board.getTile(0, 2).getOrientation());
        Assert.assertEquals(Orientation.Down, board.getTile(0, 3).getOrientation());
        Assert.assertEquals(Orientation.Down, board.getTile(0, 4).getOrientation());

    }

    @Test
    public void testTeeOnBottom() throws Exception {

        final String input = "CTTTTC,CTTTTC";

        final Board board = StringBoardBuilder.buildBoard(input);

        board.rotate(1,2);

        board.rotate(1,3);
        board.rotate(1,3);

        board.rotate(1,4);
        board.rotate(1,4);
        board.rotate(1,4);

        Assert.assertEquals(Orientation.Up, board.getTile(1, 1).getOrientation());
        Assert.assertEquals(Orientation.Right, board.getTile(1, 2).getOrientation());
        Assert.assertEquals(Orientation.Down, board.getTile(1, 3).getOrientation());
        Assert.assertEquals(Orientation.Left, board.getTile(1, 4).getOrientation());

        PerimeterStrategy strategy = new PerimeterStrategy();
        strategy.apply(board);

        Assert.assertEquals(Orientation.Up, board.getTile(1, 1).getOrientation());
        Assert.assertEquals(Orientation.Up, board.getTile(1, 2).getOrientation());
        Assert.assertEquals(Orientation.Up, board.getTile(1, 3).getOrientation());
        Assert.assertEquals(Orientation.Up, board.getTile(1, 4).getOrientation());

    }

}
