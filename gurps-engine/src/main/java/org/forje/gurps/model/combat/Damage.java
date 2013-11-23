package org.forje.gurps.model.combat;

/**
 * Documents the various attributes of an attack's damage;
 */
public interface Damage {
    /**
     * Returns the number of points of damage represented.
     *
     * @return the number of points of damage being done
     */
    public int getMagnitude();

    /**
     * Returns the type of damage done.
     *
     * @return DamageType the type of damage done.
     */
    public DamageType getDamageType();
}
