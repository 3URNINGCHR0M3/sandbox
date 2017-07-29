package org.forje.netgame.engine;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Arrays;

/**
 * A Tile represents a PieceType on the board and it's state; mainly its orientation.
 */
public class Tile {

    /**
     * The property name which will be used when the tile is rotated.
     */
    public static final String TILE_ORIENTATION_PROPERTY_NAME = "Tile.Orientation";

    /**
     * The current orientation of the tile.  Defaults to up.  This is a delta
     * the original orientation.  For example, all tiles start at Up, if it
     * rotate twice, the value will be Down.
     */
    private Orientation _orientation = Orientation.Up;

    /**
     * The exits for tile in the current orientation.  This array is updated
     * each time the tile is rotated.
     */
    final private Orientation[] _exits;

    /**
     * Manages the set of property change listeners for this tile.
     */
    final PropertyChangeSupport _propertyChangeSupport = new PropertyChangeSupport(this);

    /**
     * The PieceType.
     */
    private final PieceType _type;

    /**
     * Constructor, takes the piece type as a parameter.
     */
    public Tile(final PieceType type) {

        if (type == null) {
            throw new IllegalArgumentException();
        }

        _type = type;

        final Orientation[] typeExits = type.getExits();
        _exits = new Orientation[typeExits.length];

        for (int i = 0; i < typeExits.length; i++) {
            _exits[i] = typeExits[i];
        }

    }

    /**
     * Returns the piece type.
     *
     * @return the PieceType for the tile.
     */
    public PieceType getType() {
        return _type;
    }

    /**
     * Returns the set of exits for the tile in the current orientation.
     *
     * @return an array of Orientation objects representing the current exists.
     */
    public Orientation[] getExits() {
        return Arrays.copyOf(_exits, _exits.length);
    }

    /**
     * Rotates the tile one 90 degree motion.
     */
    public void rotate() {

        Orientation previous = _orientation;

        _orientation = _orientation.rotate();

        for (int i = 0; i < _exits.length; i++) {
            Orientation exit = _exits[i];
            _exits[i] = exit.rotate();
        }

        _propertyChangeSupport.firePropertyChange(TILE_ORIENTATION_PROPERTY_NAME, previous, _orientation);

    }


    /**
     * Adds a property change listener.
     *
     * @param pcl the PropertyChangeListener to add
     */
    public void addPropertyChangeListener(final PropertyChangeListener pcl) {
        _propertyChangeSupport.addPropertyChangeListener(pcl);
    }

    /**
     * Returns the current orientation of the tile.
     *
     * @return the current Orientation of the tile.
     */
    public Orientation getOrientation() {
        return _orientation;
    }

    public void rotate(final int turns) {

        for (int i=0; i<turns; i++) {
            rotate();
        }

    }

}
