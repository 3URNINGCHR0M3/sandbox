package surratt.minesweeper.model;

import java.lang.Math;

/**
 *
 */
public class Board {

    private final MineField _mineField;
    private final String[][] _spaces;

    public Board(final MineField mineField) {

        _mineField = mineField;

        final int width = _mineField.getWidth();
        final int height = _mineField.getHeight();

        _spaces = new String[height][width];

        populateBoard();

    }

    private void populateBoard() {

        for (int i = 0; i < _spaces.length; i++) {
            String[] row = _spaces[i];
            for (int j = 0; j < row.length; j++) {

                if (_mineField.isMine(j, i)) {
                    row[j] = "!";
                } else {
                    int count = countAdjacent(j,i);
                    row[j] = Integer.toString(count);
                }

            }
        }

    }

    private int countAdjacent(final int x, final int y) {

        final int width = _mineField.getWidth();
        final int height = _mineField.getHeight();

        int lowerX = Math.max(0, x - 1);
        int lowerY = Math.max(0, y - 1);

        int upperX = Math.min(x + 1, width - 1);
        int upperY = Math.min(y + 1, height - 1);

        int count = 0;

        for (int i = lowerX; i <= upperX; i++) {
            for (int j = lowerY; j <= upperY; j++) {
                if (_mineField.isMine(i,j)) {
                    count++;
                }
            }
        }

        return count;

    }

    public String value(final int x, final int y) {

        return _spaces[y][x];

    }

}
