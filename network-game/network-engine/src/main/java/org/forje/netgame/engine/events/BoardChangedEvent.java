package org.forje.netgame.engine.events;

import org.forje.netgame.engine.Board;
import java.util.EventObject;

/**
 * Created by Brian on 10/6/15.
 */
public class BoardChangedEvent extends EventObject {

    final int _row;
    final int _col;

    public BoardChangedEvent(final Board source, final int row, final int col) {
        super(source);
        _row = row;
        _col = col;
    }

    public int getRow() {
        return _row;
    }

    public int getCol() {
        return _col;
    }

}
