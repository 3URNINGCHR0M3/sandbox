package org.forje.gurps.model.equipment.weapons;

import org.forje.gurps.model.TechnologyLevel;

/**
 * This class encapsulates the attributes of a weapon.  It is not an actionable class, and instead represents the table
 * entry for the weapon.
 */
public class WeaponDefinitionImpl implements WeaponDefinition {

    private TechnologyLevel _technologyLevel;
    private WeaponType _weaponType;
    private String _name;
    private int _weight;
    private int _reloadWeight;
    private int _minimumStrength;
    private int _range;
    private int _accuracy;
    private int _accuracyScopeBonus;
    private int _maximumRange;
    private int _maximumEffectiveRange;
    private int _rateOfFire;
    private int _shots;
    private boolean _twohandedLight;
    private boolean _twohandedHeavy;

    @Override
    public TechnologyLevel getTechnologyLevel() {
        return _technologyLevel;
    }

    @Override
    public WeaponType getWeaponType() {
        return _weaponType;
    }

    @Override
    public String getName() {
        return _name;
    }

    @Override
    public int getWeight() {
        return _weight;
    }

    @Override
    public int getReloadWeight() {
        return _reloadWeight;
    }

    /**
     * The minimum strength to yield the weapon
     */
    @Override
    public int getMinimumStrength() {
        return _minimumStrength;
    }

    @Override
    public int getRange() {
        return _range;
    }

    @Override
    public int getAccuracy() {
        return _accuracy;
    }

    @Override
    public int getAccuracyScopeBonus() {
        return _accuracyScopeBonus;
    }

    @Override
    public int getMaximumRange() {
        return _maximumRange;
    }

    /**
     * Attacks at this range or greater (>=) will do only half damage.  May not be defined for all weapons.
     */
    @Override
    public int getMaximumEffectiveRange() {
        return _maximumEffectiveRange;
    }

    @Override
    public int getRateOfFire() {
        return _rateOfFire;
    }

    @Override
    public int getShots() {
        return _shots;
    }

    @Override
    public boolean isTwohandedLight() {
        return _twohandedLight;
    }

    @Override
    public boolean isTwohandedHeavy() {
        return _twohandedHeavy;
    }
}
