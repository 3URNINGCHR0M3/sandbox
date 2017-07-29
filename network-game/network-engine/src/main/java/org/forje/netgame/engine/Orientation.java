package org.forje.netgame.engine;

/**
 * Created by Brian on 7/21/15.
 */
public enum Orientation {

    Up(-1,0), Right(0, 1), Down(1,0), Left(0,-1);

    private final int _rowDelta;
    private final int _colDelta;

    private Orientation _opposing;

    Orientation(final int rowDelta, final int colDelta) {
        _rowDelta = rowDelta;
        _colDelta = colDelta;
    }

    public Orientation rotate() {

        final int curIndex = ordinal();
        final int newIndex = (curIndex + 1) % 4;

        return Orientation.values()[newIndex];

    }

    public int getRowDelta() {
        return _rowDelta;
    }

    public int getColDelta() {
        return _colDelta;
    }

    public Orientation getOpposing() {

        if (_opposing == null) {
            final int index = ordinal();
            final int oppIndex = (index + 2) % 4;
            _opposing = Orientation.values()[oppIndex];
        }

        return _opposing;

    }

    /**
     * Returns the number of rotation required to get from this Orientation
     * to the value passed in.
     */
    public int getRotationsRequired(final Orientation target) {

        int thisOrdinal = this.ordinal();
        int thatOrdinal = target.ordinal();


        int i = thatOrdinal - thisOrdinal;

        if (thisOrdinal > thatOrdinal) {
            i = 4 + i;
        }

        return i;

    }

}
