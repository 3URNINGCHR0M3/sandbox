package org.forje.gurps.model.equipment.weapons;

import org.forje.gurps.model.TechnologyLevel;

/**
 * Created by Brian on 4/15/15.
 */
public interface WeaponDefinition {

    TechnologyLevel getTechnologyLevel();

    WeaponType getWeaponType();

    String getName();

    int getWeight();

    int getReloadWeight();

    int getMinimumStrength();

    int getRange();

    int getAccuracy();

    int getAccuracyScopeBonus();

    int getMaximumRange();

    int getMaximumEffectiveRange();

    int getRateOfFire();

    int getShots();

    boolean isTwohandedLight();

    boolean isTwohandedHeavy();
}
