package org.forje.netgame.solver.strategies;

import org.forje.netgame.engine.Board;
import org.forje.netgame.engine.Orientation;
import org.forje.netgame.engine.PieceType;
import org.forje.netgame.engine.Tile;
import org.forje.netgame.solver.SolvingStrategy;

/**
 * This will solve the position of tee and line pieces on the line of
 * the perimeter of the board.
 */
public class PerimeterStrategy implements SolvingStrategy {

    public void apply(final Board board) {

        final int rows = board.getRows();
        final int cols = board.getColumns();

        processHorizontalEdge(board, 0);
        processHorizontalEdge(board, rows - 1);
        processVerticalEdge(board, 0);
        processVerticalEdge(board, cols - 1);

    }

    private void processVerticalEdge(final Board board,
                                     final int col) {

        int rows = board.getRows();

        for (int r = 1; r < rows; r++) {

            Tile tile = board.getTile(r, col);

            if (PieceType.line.equals(tile.getType())) {

                Orientation orientation = tile.getOrientation();
                if (Orientation.Left.equals(orientation) || Orientation.Right.equals(orientation)) {
                    tile.rotate();
                }

            }

        }

    }

    private void processHorizontalEdge(final Board board,
                                       final int row) {

        final int cols = board.getColumns();
        final Orientation targetTeeOrientation;

        if (row == 0) {
            targetTeeOrientation = Orientation.Down;
        } else {
            targetTeeOrientation = Orientation.Up;
        }

        Tile tile;
        PieceType pieceType;
        Orientation orientation;

        // handle top row
        for (int c = 1; c < cols - 1; c++) {

            tile = board.getTile(row, c);
            pieceType = tile.getType();
            orientation = tile.getOrientation();

            System.out.println("[" + row + "," + c + "] " + tile.getOrientation());

            if (PieceType.line.equals(pieceType)) {

                if (Orientation.Up.equals(orientation) || Orientation.Down.equals(orientation)) {
                    System.out.println("    rotating");
                    tile.rotate();
                }

            } else if (PieceType.tee.equals(pieceType)) {

                int rotationsRequired = orientation.getRotationsRequired(targetTeeOrientation);
                for (int i=0; i < rotationsRequired; i++) {
                    System.out.println("    rotating");
                    tile.rotate();
                }

            }

        }

    }

}
