package org.forje.gurps.model.combat;

import org.forje.gurps.model.Entity;
import org.forje.gurps.model.equipment.weapons.Weapon;

/**
* Creates an instance of the AttackResolver interface for a given attack.
*/
public class AttackResolverFactory {

    /**
    * Returns an instance of the AttackResolver interface for the parameters passed in.
     *
     * @param attacker the Entity attacking
     * @param weapon the Weapon being used to attack
     * @param targetable the target for the attack
    */
    public static AttackResolver instance(Entity attacker,
                                          Weapon weapon,
                                          Targetable targetable) {

        AttackResolver resolver = null;

        return resolver;

    }

}
