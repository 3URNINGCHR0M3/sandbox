package org.forje.gurps.model.combat;

import org.forje.gurps.model.Entity;

/**
* Creates an instance of the AttackResolver interface for a given attack.
*/
public class AttackResolverFactory {

    /**
    * Returns an instance of the AttackResolver interface for the parameters passed in.
     *
     * @param
     * @param
     * @param
    */
    public static AttackResolver instance(Entity attacker,
                                          Weapon weapon,
                                          Targetable targetable) {

        AttackResolver resolver = null;

        return resolver;

    }

}
