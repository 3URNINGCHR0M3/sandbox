package org.forje.netgame.solver;

import org.forje.netgame.engine.Board;

/**
 * Created by Brian on 9/13/15.
 */
public interface SolvingStrategy {
    void apply(Board board);
}
