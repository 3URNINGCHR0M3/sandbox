package org.forje.gurps.model;

import org.forje.gurps.model.combat.Targetable;

/**
* This is the highest level abstraction for all objects in the game environment. Any dynamic information
 * about an object which can vary from turn to turn.
*/
public interface Entity {

    /**
     * Returns the coordinates
     */
    public Coordinates getCoordinates();

    /**
     * May not be applicable for all Entities
     */
    public Posture getPosture();

    /**
     * Returns the
     */
    public Object getSubject();

}
