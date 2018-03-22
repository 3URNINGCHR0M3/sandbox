package surratt.minesweeper.model;

/**
 *
 */
public class MineField {

    private int _width;
    private int _height;

    private final byte[][] _spaces;

    public MineField(final int width, final int height) {
        _width = width;
        _height = height;
        _spaces = new byte[_height][_width];
    }

    public int getWidth() {
        return _width;
    }


    public int getHeight() {
        return _height;
    }

    public boolean isMine(final int x, final int y) {
        return _spaces[y][x] == 1;
    }

    public void placeMine(final int x, final int y) {
        _spaces[y][x] = 1;
    }

}
