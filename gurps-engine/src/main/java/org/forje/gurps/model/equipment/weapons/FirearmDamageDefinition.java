package org.forje.gurps.model.equipment.weapons;

import org.forje.gurps.model.combat.DamageType;

/**
 *
 */
public interface FirearmDamageDefinition {
    public int getDiceCount();
    public int getModifier();
    public DamageType getDamageType();
}
