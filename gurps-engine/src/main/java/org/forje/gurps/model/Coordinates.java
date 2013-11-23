package org.forje.gurps.model;

/**
* An immutable class representing a position in three-dimenstional space.
*/
public class Coordinates {

    private final int _x;
    private final int _y;
    private final int _z;

    public Coordinates(final int x, final int y, final int z) {
        _x = x;
        _y = y;
        _z = z;
    }

    public int getX() {
        return _x;
    }

    public int getY() {
        return _y;
    }

    public int getZ() {
        return _z;
    }

    public Coordinates move(final int deltaX, final int deltaY, final int deltaZ) {
        return new Coordinates(_x + deltaX, _y + deltaY, _z + deltaZ);
    }

}
