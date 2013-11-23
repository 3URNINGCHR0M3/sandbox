package org.forje.gurps.model.combat;

/**
* An instance of a weapon, providing access to all metrics for the type as well as tracking ammo state and any other
* instance related data.
*/
public interface Weapon {
    public WeaponState getWeaponState();
}
