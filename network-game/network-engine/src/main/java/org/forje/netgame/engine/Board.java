package org.forje.netgame.engine;

/**
 * Created by Brian on 7/21/15.
 */
public class Board {

    private final int _rows;
    private final int _columns;

    private Tile[][] _tiles;


    public Board(final int rows, final int columns) {
        _rows = rows;
        _columns = columns;
        _tiles=new Tile[rows][columns];
    }

    public int getRows() {
        return _rows;
    }

    public int getColumns() {
        return _columns;
    }

    public void setTile(final int row, final int col, final Tile tile) {
        _tiles[row][col] = tile;
    }

    public Tile getTile(final int row, final int col) {
        return _tiles[row][col];
    }

    public void rotate(final int row,
                       final int col) {

        getTile(row, col).rotate();

        // @todo - throw BoardChangedEvent

    }

}
