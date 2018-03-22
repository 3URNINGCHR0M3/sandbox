package surratt.minesweeper.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 *
 */
public class MineFieldFactoryTest {

    @Test
    void fieldDimensions() {

        final MineFieldFactory factory = new MineFieldFactory();
        final MineField mineField = factory.produce(15,10, 20);
        assertEquals(15, mineField.getWidth());
        assertEquals(10, mineField.getHeight());

    }

    // (from Windows) limits: 30 times 24 with 667 mines
    // 30 x 24 = 720
    // 667 = 93% mines

   @Test
    void maxWidth() {
       final MineFieldFactory factory = new MineFieldFactory();
       try {
           factory.produce(31, 10, 20);
           fail("Expected an exception.");
       } catch (IllegalArgumentException e) {
           assertEquals("The width is limited to a value of 30.", e.getMessage());
       }
   }

    @Test
    void maxHeight() {
        final MineFieldFactory factory = new MineFieldFactory();
        try {
            factory.produce(5, 25, 20);
            fail("Expected an exception.");
        } catch (IllegalArgumentException e) {
            assertEquals("The height is limited to a value of 24.", e.getMessage());
        }
    }

    @Test
    void maxMines() {
        final MineFieldFactory factory = new MineFieldFactory();
        try {
            factory.produce(30, 24, 668);
            fail("Expected an exception.");
        } catch (IllegalArgumentException e) {
            assertEquals("The number of mines is limited to a value of 667.", e.getMessage());
        }
    }

    @Test
    void testMoreMinesThanSpaces() throws Exception {
        final MineFieldFactory factory = new MineFieldFactory();
        try {
            factory.produce(10, 10, 101);
            fail("Expected an exception.");
        } catch (IllegalArgumentException e) {
            assertEquals("The number of mines [101] exceeds the number of spaces [100].", e.getMessage());
        }
    }

    @Test
    void testProducesSpecifiedNumberOfMines() {

        final MineFieldFactory factory = new MineFieldFactory();
        final int width = 10;
        final int height = 5;
        final MineField mineField = factory.produce(width, height, 25);

        int minesFound = 0;

        for (int i = 0; i < (height - 1); i++) {
            for (int j = 0; j < (width - 1); j++) {
                boolean isMine = mineField.isMine(j,i);
                if (isMine) {
                    minesFound++;
                }
            }
        }

        assertEquals(25, minesFound);

    }

}
