package org.forje.gurps.model.combat;

import junit.framework.Assert;
import org.junit.Test;

public class DamageTypeMapTest {

    @Test
    public void testTableDefinition() throws Exception {
        Assert.assertEquals("cut", DamageType.Cutting, DamageTypeMap.getInstance().getValue("cut"));
        Assert.assertEquals("burn", DamageType.Burning, DamageTypeMap.getInstance().getValue("burn"));
        Assert.assertEquals("cr", DamageType.Crushing, DamageTypeMap.getInstance().getValue("cr"));
        Assert.assertEquals("pi++", DamageType.HugePiercing, DamageTypeMap.getInstance().getValue("pi++"));
        Assert.assertEquals("imp", DamageType.Impaling, DamageTypeMap.getInstance().getValue("imp"));
        Assert.assertEquals("pi+", DamageType.LargePiercing, DamageTypeMap.getInstance().getValue("pi+"));
        Assert.assertEquals("pi", DamageType.Piercing, DamageTypeMap.getInstance().getValue("pi"));
        Assert.assertEquals("pi-", DamageType.SmallPiercing, DamageTypeMap.getInstance().getValue("pi-"));
    }

    @Test
    public void testThrowsIllegalArgument() throws Exception {

        // this also serves to test this functionality for the GenericMap class

        try {
            DamageTypeMap.getInstance().getValue("!!!");
            Assert.fail("Expected an exception");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("[!!!] does not match any known type",e.getMessage());

        }
    }



}
