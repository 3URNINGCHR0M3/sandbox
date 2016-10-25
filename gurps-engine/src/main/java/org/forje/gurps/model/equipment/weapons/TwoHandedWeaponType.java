package org.forje.gurps.model.equipment.weapons;

public enum TwoHandedWeaponType {

    Light(2), Heavy(3);

    /**
    * The multiple of the Strength requirement need to wield the weapon efectively one handed.
    */
    private final int _strengthModifier;

    private TwoHandedWeaponType(final int strengthModifier) {
        _strengthModifier = strengthModifier;
    }
}
