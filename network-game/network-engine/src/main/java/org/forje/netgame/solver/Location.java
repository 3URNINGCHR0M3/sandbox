package org.forje.netgame.solver;

import org.forje.netgame.engine.Board;

/**
 * This is a token which encapsulates all information about a location:
 * The Board, the coordinates and the TileAnalysis meta data
 */
public class Location {

    private final Board _board;
    private final int _row;
    private final int _col;
    private final TileAnalysis _tileAnalysis;

    public Location(final Board board,
                    final int row,
                    final int col,
                    final TileAnalysis tileAnalysis) {
        _board = board;
        _row = row;
        _col = col;
        _tileAnalysis = tileAnalysis;
    }

    public Board getBoard() {
        return _board;
    }

    public int getRow() {
        return _row;
    }

    public int getCol() {
        return _col;
    }

    public TileAnalysis getTileAnalysis() {
        return _tileAnalysis;
    }

}
