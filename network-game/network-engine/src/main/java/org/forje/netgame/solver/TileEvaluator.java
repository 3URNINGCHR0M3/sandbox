package org.forje.netgame.solver;

import org.forje.netgame.engine.Board;
import org.forje.netgame.engine.Orientation;
import org.forje.netgame.engine.Tile;

import java.util.Arrays;
import java.util.List;

/**
 * This class is responsible for evaluating the state of a tile.
 * That is, are all exit points connected.
 */
public class TileEvaluator {

    private final Board _board;

    public TileEvaluator(final Board board) {
        _board = board;
    }

    /**
     * Determines if all exists for the tile are connected to exists
     * adjacent tiles.  Return true if they are all connected, false
     * otherwise.
     *
     * @param row the row to check.
     * @param col the column to check.
     *
     * @return true if all exits points are connected, false otherwise.
     */
    public boolean check(final int row, final int col) {

        final Tile tile = _board.getTile(row, col);

        final Orientation[] exits = tile.getExits();

        boolean returnValue = true;

        Orientation orientation;
        int newRow;
        int newCol;
        for (int i = 0; i < exits.length; i++) {

            orientation = exits[i];

            newRow = row + orientation.getRowDelta();
            newCol = col + orientation.getColDelta();

            boolean outOfBounds = outOfBounds(newRow, newCol);
            if (outOfBounds) {
                returnValue = false;
                break;
            }

            Tile newTile = _board.getTile(newRow, newCol);

            Orientation opposing = orientation.getOpposing();

            List<Orientation> newTileExits = Arrays.asList(newTile.getExits());
            if (!newTileExits.contains(opposing)) {
                returnValue = false;
                break;
            }

        }

        return returnValue;

    }

    /**
     * Determines if the cell identified by the row and col parameters
     * are out of bounds for the current board size.  Returns true if
     * the cell would be out of bounds, false otherwise.
     *
     * @param row the row to check.
     * @param col the column to check.
     */
    private boolean outOfBounds(final int row, final int col) {

        if (row < 0) {
            return true;
        }

        if (col < 0) {
            return true;
        }

        if (col >= _board.getColumns()) {
            return true;
        }

        if (row >= _board.getRows()) {
            return true;
        }

        return false;

    }

}
