package org.forje.gurps.model.equipment.weapons;

import org.forje.gurps.model.equipment.weapons.WeaponState;

import java.util.List;

/**
* An instance of a weapon, providing access to all metrics for the type as well as tracking ammo state and any other
* instance related data.
*/
public interface Weapon {

    public WeaponState getWeaponState();

    /**
     * Returns the set of States which the weapon can transition to.
     */
    public List<WeaponState> getStateTransitions();


}
