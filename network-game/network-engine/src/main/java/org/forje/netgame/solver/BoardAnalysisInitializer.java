package org.forje.netgame.solver;

import org.forje.netgame.engine.Board;

/**
 * Created by Brian on 10/6/15.
 */
public class BoardAnalysisInitializer {

    public BoardAnalysis prepare(final Board board) {

        final int rows = board.getRows();
        final int cols = board.getColumns();

        final BoardAnalysis boardAnalysis = new BoardAnalysis(rows, cols);
        boardAnalysis.initialize();

        return boardAnalysis;

    }

}
