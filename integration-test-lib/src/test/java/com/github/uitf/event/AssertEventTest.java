package com.github.uitf.event;

import org.junit.Assert;
import org.junit.Test;

import java.util.EventObject;

/**
 *
 */
public class AssertEventTest {

    @Test
    public void testExtendsEventOPbject() throws Exception {

        final Class<AssertEvent> subject = AssertEvent.class;
        Assert.assertEquals(true, EventObject.class.isAssignableFrom(subject));

    }

    @Test
    public void testObjectBooleanStringConst() throws Exception {
        final AssertEvent event = new AssertEvent(this, false, "kaboom");
    }

    @Test
    public void testConstructorGetResult() throws Exception {

        final AssertEvent event = new AssertEvent(this, true, "kapow");

        Assert.assertEquals(true, event.getIsTrue());

    }

    @Test
    public void testConstructorGetMessage() throws Exception {
        final AssertEvent event = new AssertEvent(this, true, "blam!");
        Assert.assertEquals("blam!", event.getMessage());
    }

    @Test
    public void testObjectMessageConst() throws Exception {
        final AssertEvent event = new AssertEvent(this, "wham");
        Assert.assertEquals(false, event.getIsTrue());
    }

    @Test
    public void testToString() throws Exception {
        final AssertEvent event = new AssertEvent(this, true, "splat!!");
        Assert.assertEquals("splat!!: true", event.toString());
    }

}
