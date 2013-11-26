package org.forje.gurps.model.combat;

import junit.framework.Assert;
import org.junit.Test;

/**
 * These tests will make sure that WeaponDamageDefinitionFactory properly parses a definition String
 */
public class WeaponDamageDefinitionFactoryTest {

    @Test
    public void testAlpha() throws Exception {
        WeaponDamageDefinition wdd = WeaponDamageDefinitionFactory.instance("sw+2 cut");
        Assert.assertEquals("sw+2 cut - melee type", MeleeType.Swinging, wdd.getMeleeType());
        Assert.assertEquals("sw+2 cut - damage modifier", 2, wdd.getDamageModifier());
        Assert.assertEquals("sw+2 cut - damage type", DamageType.Cutting, wdd.getDamageType());
    }

    @Test
    public void testBeta() throws Exception {

    }

    @Test
    public void testThrowsExceptionOnUnmatchable() throws Exception {
        try {
            WeaponDamageDefinitionFactory.instance("garbage in, garbage out");
            Assert.fail("Expected an exception");
        } catch (IllegalStateException e) {

        }

    }

}
