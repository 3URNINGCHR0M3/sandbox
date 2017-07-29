package org.forje.netgame.solver;

import org.forje.netgame.engine.Board;

/**
 * An interface for code which must evaluate a location on the board.
 */
public interface LocationVisitor {

    public void visit(Location location);

}
