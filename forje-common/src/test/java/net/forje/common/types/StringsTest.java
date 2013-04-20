package net.forje.common.types;


import org.junit.Assert;
import org.junit.Test;

public class StringsTest {

    @Test
    public void testWithString() throws Exception {

        Assert.assertEquals(false, Strings.isEmpty("blah blah"));

    }

    @Test
    public void testNull() throws Exception {
        Assert.assertEquals(true, Strings.isEmpty(null));
    }

    @Test
    public void testEmptyString() throws Exception {
        Assert.assertEquals(true, Strings.isEmpty(""));
    }


    @Test
    public void testAllSpacesString() throws Exception {
        Assert.assertEquals(true, Strings.isEmpty("   "));
    }

}
