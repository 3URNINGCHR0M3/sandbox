package org.forje.gurps.model.equipment.weapons;

import org.forje.gurps.model.combat.DamageType;
import org.forje.gurps.model.combat.MeleeType;

/**
 * This defines the interface for documenting the damage done by a weapon.
 */
public interface WeaponDamageDefinition {
    public MeleeType getMeleeType();

    public int getDamageModifier();

    DamageType getDamageType();
}
