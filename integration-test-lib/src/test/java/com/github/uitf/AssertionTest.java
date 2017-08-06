package com.github.uitf;

import com.github.uitf.event.AssertionEvent;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 */
public class AssertionTest {


    @Test
    public void testOnlyAllowOneListener() throws Exception {

        final AssertionEvalTest.TestListener alpha = new AssertionEvalTest.TestListener();
        final AssertionEvalTest.TestListener beta = new AssertionEvalTest.TestListener();

        Assertion.reset();
        Assertion.addListener(alpha);
        try {
            Assertion.addListener(beta);
            Assert.fail("Expected an exception.");
        } catch (IllegalStateException e) {

        }

    }

    @Test
    public void testAddNullListenerThrowsException() throws Exception {
        Assertion.reset();

        try {
            Assertion.addListener(null);
            Assert.fail("Expected an exception.");
        } catch (IllegalArgumentException e) {

        }
    }

    @Test
    public void testAddAssertionEventListener() throws Exception {
        Assertion.reset();
        final AssertionEvalTest.TestListener listener = new AssertionEvalTest.TestListener();
        Assertion.addListener(listener);
    }

    @Test
    public void testRemoveAssertionEventListener() throws Exception {
        Assertion.reset();
        Assertion.addListener(new AssertionEvalTest.TestListener());
        Assertion.reset();
        // if reset has not worked properly, this method call will result in an exception
        Assertion.addListener(new AssertionEvalTest.TestListener());
    }

    @Test
    public void testReportsMultipleAssertions() throws Exception {

        Assertion.reset();
        final AssertionEvalTest.TestListener listener = new AssertionEvalTest.TestListener();
        Assertion.addListener(listener);

        Assert.assertEquals(0, listener.getAssertions().size());

        Assertion.equals("message", "expected","expected");

        Assert.assertEquals(1, listener.getAssertions().size());

        Assertion.equals("message", "expected","actual");

        Assert.assertEquals(2, listener.getAssertions().size());

    }

    @Test
    public void testHandleNullNotNull() throws Exception {
        Assertion.reset();
        final AssertionEvalTest.TestListener listener = new AssertionEvalTest.TestListener();
        Assertion.addListener(listener);

        Assertion.equals("not null v null", null, new Object());

        final AssertionEvent event = listener.getLast();
        Assert.assertEquals(false, event.getIsTrue());
    }

    @Test
    public void testHandleNotNullNull() throws Exception {

        Assertion.reset();
        final AssertionEvalTest.TestListener listener = new AssertionEvalTest.TestListener();
        Assertion.addListener(listener);

        Assertion.equals("not null v null", new Object(), null);

        final AssertionEvent event = listener.getLast();
        Assert.assertEquals(false, event.getIsTrue());

    }

    @Test
    public void testHandleNullNull() throws Exception {

        Assertion.reset();
        final AssertionEvalTest.TestListener listener = new AssertionEvalTest.TestListener();
        Assertion.addListener(listener);

        Assertion.equals("null v null", null, null);

        final AssertionEvent event = listener.getLast();
        Assert.assertEquals(true, event.getIsTrue());

    }



}
