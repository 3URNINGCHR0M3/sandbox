package surratt.minesweeper.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 */
public class BoardTest {

    @Test
    void singleMine() {

        final MineField mineField = new MineField(3, 3);
        mineField.placeMine(1, 1);

        final Board board = new Board(mineField);

        testBoard(board,
                new String[][] {
                        {"1","1","1"},
                        {"1","!","1"},
                        {"1","1","1"},
                });

//        assertEquals("1", board.value(0,0));
//        assertEquals("1", board.value(0,1));
//        assertEquals("1", board.value(0,2));
//
//        assertEquals("1", board.value(1,0));
//        assertEquals("1", board.value(1,2));
//
//        assertEquals("1", board.value(2,0));
//        assertEquals("1", board.value(2,1));
//        assertEquals("1", board.value(2,2));
//
//        assertEquals("!", board.value(1,1));

    }

    private void testBoard(Board board, String[][] values) {

        for (int i = 0; i < values.length; i++) {

            String[] row = values[i];
            for (int j = 0; j < row.length; j++) {
                final String expected = row[j];
                final String actual = board.value(j, i);
                assertEquals(expected, actual, "x,y = [" + j + "][" + i + "]");
            }

        }

    }

    @Test
    void twoAdjacentMines() {

        final MineField mineField = new MineField(4, 3);

        mineField.placeMine(1, 1);
        mineField.placeMine(2, 1);

        final Board board = new Board(mineField);

        testBoard(board,
                new String[][] {
                        {"1","2","2","1"},
                        {"1","!","!","1"},
                        {"1","2","2","1"},
                });

//        assertEquals("1", board.value(0,0));
//        assertEquals("2", board.value(1,0));
//        assertEquals("2", board.value(2,0));
//        assertEquals("1", board.value(3,0));
//
//        assertEquals("1", board.value(0,1));
//        assertEquals("!", board.value(1,1));
//        assertEquals("!", board.value(2,1));
//        assertEquals("1", board.value(3,1));
//
//        assertEquals("1", board.value(0,2));
//        assertEquals("2", board.value(1,2));
//        assertEquals("2", board.value(2,2));
//        assertEquals("1", board.value(3,2));

    }

    @Test
    void betweenTwoMines() {

        final MineField mineField = new MineField(5, 3);

        mineField.placeMine(1, 1);
        mineField.placeMine(3, 1);

        final Board board = new Board(mineField);

        testBoard(board,
                new String[][] {
                        {"1","1","2","1","1"},
                        {"1","!","2","!","1"},
                        {"1","1","2","1","1"},
                });

    }

    @Test
    void surrounded() {
        final MineField mineField = new MineField(3, 3);
        mineField.placeMine(0, 0);
        mineField.placeMine(1, 0);
        mineField.placeMine(2, 0);
        mineField.placeMine(0, 1);
        mineField.placeMine(2, 1);
        mineField.placeMine(0, 2);
        mineField.placeMine(1, 2);
        mineField.placeMine(2, 2);

        final Board board = new Board(mineField);

        testBoard(board,
                new String[][] {
                        {"!","!","!"},
                        {"!","8","!"},
                        {"!","!","!"},
                });

//        assertEquals("!", board.value(0,0));
//        assertEquals("!", board.value(0,1));
//        assertEquals("!", board.value(0,2));
//
//        assertEquals("!", board.value(1,0));
//        assertEquals("!", board.value(1,2));
//
//        assertEquals("!", board.value(2,0));
//        assertEquals("!", board.value(2,1));
//        assertEquals("!", board.value(2,2));
//
//        assertEquals("8", board.value(1,1));

    }

}
