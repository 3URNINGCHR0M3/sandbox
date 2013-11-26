package org.forje.gurps.model.combat;

import junit.framework.Assert;
import org.junit.Test;

public class MeleeTypeMapTest {

    @Test
    public void testTableDefinition() throws Exception {
        Assert.assertEquals("sw", MeleeType.Swinging, MeleeTypeMap.getInstance().getValue("sw"));
        Assert.assertEquals("thr", MeleeType.Thrusting, MeleeTypeMap.getInstance().getValue("thr"));
    }

}
