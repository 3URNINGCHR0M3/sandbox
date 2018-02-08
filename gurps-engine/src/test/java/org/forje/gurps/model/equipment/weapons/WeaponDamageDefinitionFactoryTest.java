package org.forje.gurps.model.equipment.weapons;

import junit.framework.Assert;
import org.forje.gurps.model.combat.DamageType;
import org.forje.gurps.model.combat.MeleeType;
import org.junit.Test;

/**
 * These tests will make sure that WeaponDamageDefinitionFactory properly parses a definition String
 */
public class WeaponDamageDefinitionFactoryTest {

    @Test
    public void testAble() throws Exception {
        MuscleBasedDamageDefinition wdd = WeaponDamageDefinitionFactory.parseMuscleBased("sw+2 cut");
        validateMeleeWeapon(wdd, "sw+2 cut", MeleeType.Swinging, 2, DamageType.Cutting);
    }

    @Test
    public void testBeta() throws Exception {
        MuscleBasedDamageDefinition wdd = WeaponDamageDefinitionFactory.parseMuscleBased("sw+3 cr");
        validateMeleeWeapon(wdd, "sw+3 cr", MeleeType.Swinging, 3, DamageType.Crushing);
    }

    @Test
    public void testCharlie() throws Exception {
        MuscleBasedDamageDefinition wdd = WeaponDamageDefinitionFactory.parseMuscleBased("thr-1 cr");
        validateMeleeWeapon(wdd, "thr-1 cr", MeleeType.Thrusting, -1, DamageType.Crushing);
    }

    @Test
    public void testDelta() throws Exception {
        MuscleBasedDamageDefinition wdd = WeaponDamageDefinitionFactory.parseMuscleBased("thr+1 imp");
        validateMeleeWeapon(wdd, "thr+1 imp", MeleeType.Thrusting, 1, DamageType.Impaling);
    }

    @Test
    public void testEcho() throws Exception {
        MuscleBasedDamageDefinition wdd = WeaponDamageDefinitionFactory.parseMuscleBased("thr+1 imp");
        validateMeleeWeapon(wdd, "thr+1 imp", MeleeType.Thrusting, 1, DamageType.Impaling);
    }

//

//
//

    @Test
    public void testFoxTrot() throws Exception {
        FirearmDamageDefinition wdd = WeaponDamageDefinitionFactory.parseFirearm("2d+2 pi");
        Assert.assertNotNull(wdd);
        validateFirearm(wdd, "2d+2 pi", 2, DamageType.Piercing, 2);
    }

    @Test
    public void testGolf() throws Exception {
        FirearmDamageDefinition wdd = WeaponDamageDefinitionFactory.parseFirearm("1d+3 pi+");
        validateFirearm(wdd, "1d+3 pi+", 1, DamageType.LargePiercing, 3);
    }

    @Test
    public void testHotel() throws Exception {
        FirearmDamageDefinition wdd = WeaponDamageDefinitionFactory.parseFirearm("3d-1 pi");
        validateFirearm(wdd, "3d-1 pi", 3, DamageType.Piercing, -1);
    }

    @Test
    public void testIndigo() throws Exception {
        FirearmDamageDefinition wdd = WeaponDamageDefinitionFactory.parseFirearm("5d pi++");
        validateFirearm(wdd, "5d pi++", 5, DamageType.HugePiercing, 0);
    }


    private void validateFirearm(final FirearmDamageDefinition wdd,
                                 final String test,
                                 final int dice,
                                 final DamageType damageType,
                                 final int modifier) {
        Assert.assertEquals(test + " - dice", dice, wdd.getDiceCount());
        Assert.assertEquals(test + " - damage type", damageType, wdd.getDamageType());
        Assert.assertEquals(test + " - modifier", modifier, wdd.getModifier());
    }

    @Test
    public void testThrowsExceptionOnUnmatchable() throws Exception {
        try {
            WeaponDamageDefinitionFactory.parseMuscleBased("garbage in, garbage out");
            Assert.fail("Expected an exception");
        } catch (IllegalStateException e) {

        }

    }


    private void validateMeleeWeapon(final MuscleBasedDamageDefinition wdd,
                                     final String testName,
                                     final MeleeType meleeType,
                                     final int modifier,
                                     final DamageType damageType) {

        Assert.assertEquals(testName + " - melee type", meleeType, wdd.getMeleeType());
        Assert.assertEquals(testName + " - damage modifier", modifier, wdd.getDamageModifier());
        Assert.assertEquals(testName + " - damage type", damageType, wdd.getDamageType());

    }

}
