package org.forje.gurps.model.combat;

import org.forje.gurps.model.TechnologyLevel;

/**
 * This class encapsulates the attributes of a weapon.  It is not an actionable class, and instead represents the table
 * entry for the weapon.
 */
public class WeaponDefinition {

    private TechnologyLevel _technologyLevel;
    private WeaponType _weaponType;
    private String _name;
    private int _weight;
    private int _reloadWeight;
    /**
     * The minumim strength to yeild the weapon
     */
    private int _minimumStrength;
    private int _range;
    private int _accuracy;
    private int _accuracyScopeBonus;
    private int _maximumRange;
    /**
     * Attacks at this range or greater (>=) will do only half damage.  May not be defined for all weapons.
     */
    private int _maximumEffectiveRange;
    private int _rateOfFire;
    private int _shots;
    private boolean _twohandedLight;
    private boolean _twohandedHeavy;

}
