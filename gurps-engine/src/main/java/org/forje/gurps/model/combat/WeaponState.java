package org.forje.gurps.model.combat;

/**
* <ul>
 * <li>Ready - Weapon can be fired</li>
 * <li>Empty - Weapon needs to be reloaded</li>
 * <li>Reloading - Is in the process of reloading</li>
 * <li>Depleted - Weapon is empty and has no remaining ammo reserves</li>
 * <li>Destroyed - Weapon has been destroyed and can not be </li>
 * </ul>
*/
public enum WeaponState {
    Ready, Empty, Reloading, Depleted, Destroyed
}
