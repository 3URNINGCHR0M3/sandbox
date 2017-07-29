package org.forje.netgame.util;

import org.forje.netgame.engine.Board;
import org.forje.netgame.engine.PieceType;
import org.forje.netgame.engine.Tile;

import java.util.HashMap;
import java.util.Map;

/**
 * Takes a string of characters and builds a Board object to match.
 */
public class StringBoardBuilder {

    private static Map<String, PieceType> STRING_TO_PIECE_MAP =
            new HashMap<String, PieceType>(PieceType.values().length);

    static {
        STRING_TO_PIECE_MAP.put("E", PieceType.end);
        STRING_TO_PIECE_MAP.put("C", PieceType.corner);
        STRING_TO_PIECE_MAP.put("L", PieceType.line);
        STRING_TO_PIECE_MAP.put("P", PieceType.plus);
        STRING_TO_PIECE_MAP.put("T", PieceType.tee);
    }

    public static Board buildBoard(final String input) {

        final String[] table = input.split(",");

        final int rows = table.length;
        final int cols = table[0].length();

        Board board = new Board(rows,cols );
        String letter;
        String row;

        for (int r = 0; r < rows; r++) {

            row = table[r];

            if (row.length() != cols) {
                throw new IllegalArgumentException("Number of columns in row [" + (r + 1) + "] is inconsistent.");
            }

            for (int c = 0; c < cols; c++) {
                letter = Character.toString(row.charAt(c));

                if (" ".equals(letter)) {
                    continue;
                }

                PieceType pieceType = STRING_TO_PIECE_MAP.get(letter);
                if (pieceType == null) {
                    throw new IllegalArgumentException("Unsupported character [" + letter + "] at [" + (r+1) + "," + (c+1) + "].");
                }

                board.setTile(r, c, new Tile(pieceType));

            }

        }

        return board;

    }

}
