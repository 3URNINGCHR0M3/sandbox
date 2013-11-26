package org.forje.gurps.model.combat;

/**
 * This defines the interface for documenting the damage done by a weapon.
 */
public interface WeaponDamageDefinition {
    public MeleeType getMeleeType();

    public int getDamageModifier();

    DamageType getDamageType();
}
