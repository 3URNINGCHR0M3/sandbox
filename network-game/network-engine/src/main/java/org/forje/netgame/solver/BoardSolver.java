package org.forje.netgame.solver;

import org.forje.netgame.engine.Board;

/**
 * Created by Brian on 9/10/15.
 */
public class BoardSolver {


    private final Board _board;
    private final TileAnalysis[][] _meta;


    public BoardSolver(final Board board) {

        _board = board;


        final int rows = _board.getRows();
        final int cols = _board.getColumns();

        _meta = new TileAnalysis[_board.getRows()][_board.getColumns()];

        for (int r=0; r < rows; r++) {
            for (int c=0; c < cols; c++) {



            }
        }

    }


}
