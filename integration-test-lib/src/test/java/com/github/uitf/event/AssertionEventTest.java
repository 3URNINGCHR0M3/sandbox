package com.github.uitf.event;

import org.junit.Assert;
import org.junit.Test;

import java.util.EventObject;

/**
 *
 */
public class AssertionEventTest {

    @Test
    public void testExtendsEventOPbject() throws Exception {

        final Class<AssertionEvent> subject = AssertionEvent.class;
        Assert.assertEquals(true, EventObject.class.isAssignableFrom(subject));

    }

    @Test
    public void testObjectBooleanStringConst() throws Exception {
        final AssertionEvent event = new AssertionEvent(this, false, "kaboom");
    }

    @Test
    public void testConstructorGetResult() throws Exception {

        final AssertionEvent event = new AssertionEvent(this, true, "kapow");

        Assert.assertEquals(true, event.getIsTrue());

    }

    @Test
    public void testConstructorGetMessage() throws Exception {
        final AssertionEvent event = new AssertionEvent(this, true, "blam!");
        Assert.assertEquals("blam!", event.getMessage());
    }

    @Test
    public void testObjectMessageConst() throws Exception {
        final AssertionEvent event = new AssertionEvent(this, "wham");
        Assert.assertEquals(false, event.getIsTrue());
    }

    @Test
    public void testToString() throws Exception {
        final AssertionEvent event = new AssertionEvent(this, true, "splat!!");
        Assert.assertEquals("splat!!: true", event.toString());
    }

    @Test
    public void testMessageCannotBeEmptyString() throws Exception {

        try {
            new AssertionEvent(this, false, "");
            Assert.fail("Expected an exception.");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Message cannot be null or empty String.", e.getMessage());
        }

    }

    @Test
    public void testMessageCannotBeNull() throws Exception {
        try {
            new AssertionEvent(this, false, null);
            Assert.fail("Expected an exception.");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Message cannot be null or empty String.", e.getMessage());
        }
    }

    @Test
    public void testMessageCannotBeAllSpaces() throws Exception {
        try {
            new AssertionEvent(this, false, "    ");
            Assert.fail("Expected an exception.");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Message cannot be null or empty String.", e.getMessage());
        }
    }

}
