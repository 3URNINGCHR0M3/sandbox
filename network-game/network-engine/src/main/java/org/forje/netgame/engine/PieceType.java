package org.forje.netgame.engine;

/**
 * Created by Brian on 7/21/15.
 */
public enum PieceType {

    corner(new Orientation[] {Orientation.Up,Orientation.Right}),
    line(new Orientation[] {Orientation.Up,Orientation.Down}),
    end(new Orientation[] {Orientation.Up}),
    tee(new Orientation[] {Orientation.Up,Orientation.Right,Orientation.Left}),
    plus(new Orientation[] {Orientation.Up,Orientation.Right,Orientation.Down,Orientation.Left});

    private PieceType(final Orientation[] exits) {
        _exits = exits;
    }

    /**
     * This is not ideal, the array could be edited in place.
     */
    private Orientation[] _exits;


    Orientation[] getExits() {
        return _exits;
    }

}
