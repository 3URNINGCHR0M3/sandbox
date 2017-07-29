package org.forje.netgame.solver;

/**
 * Created by Brian on 10/6/15.
 */
public class BoardAnalysis {

    private final int _rows;
    private final int _cols;
    private final TileAnalysis[][] _tiles;
    boolean _initialized = false;

    public BoardAnalysis(final int rows, final int cols) {
        _rows = rows;
        _cols = cols;
        _tiles = new TileAnalysis[_rows][_cols];
    }

    public TileAnalysis getTile(final int row, final int col) {
        return _tiles[row][col];
    }


    public void initialize() {

        if (_initialized == true) {
            throw new IllegalStateException();
        }

        for (int i = 0; i < _tiles.length; i++) {

            TileAnalysis[] tiles = _tiles[i];
            for (int j = 0; j < tiles.length; j++) {
                tiles[j] = new TileAnalysis();
            }

        }

        _initialized = true;

    }


}
