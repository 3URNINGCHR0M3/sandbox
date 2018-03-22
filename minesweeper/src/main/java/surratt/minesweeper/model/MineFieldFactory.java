package surratt.minesweeper.model;

import java.util.Random;

/**
 *
 */
public class MineFieldFactory {

    public static final int MAX_WIDTH = 30;
    public static final int MAX_HEIGHT = 24;
    public static final int MAX_MINES = 667;

    public MineField produce(final int width,
                             final int height,
                             final int mines) {

        if (width > MAX_WIDTH) {
            throw new IllegalArgumentException("The width is limited to a value of 30.");
        }

        if (height > MAX_HEIGHT) {
            throw new IllegalArgumentException("The height is limited to a value of 24.");
        }

        if (mines > MAX_MINES) {
            throw new IllegalArgumentException("The number of mines is limited to a value of 667.");
        }

        int product = width * height;

        if (mines > product) {
            throw new IllegalArgumentException("The number of mines ["+mines+"] exceeds the number of spaces ["+product+"].");
        }

        final MineField field = new MineField(width, height);

        deploy(field, mines);

        return field;

    }

    private void deploy(final MineField field, final int mines) {

        int minesPlaced = 0;
        final int heightLimit = field.getHeight() - 1;
        final int widthLimit = field.getWidth() - 1;

        final Random random = new Random(System.currentTimeMillis());

        while ( minesPlaced < mines) {

            int x = random.nextInt(widthLimit);
            int y = random.nextInt(heightLimit);

            if (!field.isMine(x, y)) {
                field.placeMine(x,y);
                minesPlaced++;
            }

        }

    }

}
