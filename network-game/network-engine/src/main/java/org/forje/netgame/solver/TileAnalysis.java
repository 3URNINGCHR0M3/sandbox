package org.forje.netgame.solver;

import org.forje.netgame.engine.Orientation;

import java.util.ArrayList;
import java.util.List;

/**
 * This class stores information about the state of a time.  It is kept
 * in a parallel structure and does not have a directl linke to the tile.
 * It does not calcaulate any information about a time, just store that
 * information.
 */
public class TileAnalysis {

    private boolean _solved = false;
    private double _confidence = 0;
    private boolean _isBlank = true;
    private final List<Orientation> _availableMoves;

    public TileAnalysis() {
        _availableMoves = new ArrayList<Orientation>(4);
    }

    public void setConfidence(final double confidence) {

        if (confidence > 1d) {
            throw new IllegalArgumentException("Confidence may not be greater than 1.");
        }

        if (confidence < 0d) {
            throw new IllegalArgumentException("Confidence may not be less than 0.");
        }

        _confidence = confidence;
        _solved = (_confidence >= 1d);

    }


    public double getConfidence() {
        return _confidence;
    }

    public boolean isSolved() {
        return _solved;
    }

    public boolean isBlank() {
        return _isBlank;
    }

    public void setBlank(boolean isBlank) {
        _isBlank = isBlank;
    }

    public void setAvailableMoves(final List<Orientation> availableMoves) {
        _availableMoves.clear();
        _availableMoves.addAll(availableMoves);
    }

    public List<Orientation> getAvailableMoves() {

        final ArrayList<Orientation> temp = new ArrayList<Orientation>(_availableMoves.size());
        temp.addAll(_availableMoves);

        return temp;
    }

}
