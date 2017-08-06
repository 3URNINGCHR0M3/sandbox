package com.github.uitf;

import com.github.uitf.event.AssertionEvent;
import com.github.uitf.event.AssertionEventListener;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 *  This test will verify the correctness of all the supported assertions.
 */
public class AssertionEvalTest {

    static class TestListener implements AssertionEventListener {

        private final List _assertions = new ArrayList();
        private AssertionEvent _failure;
        private AssertionEvent _last;

        public void assertion(final AssertionEvent event) {
            _last = event;
            _assertions.add(event);
        }

        public void failure(final AssertionEvent event) {
            if (_failure != null) {

            } else {
                _failure = event;
            }
        }

        public List getAssertions() {
            return _assertions;
        }

        public AssertionEvent getFailure() {
            return _failure;
        }

        public AssertionEvent getLast() {
            return _last;
        }

    }

    private TestListener _listener = new TestListener();

    @Before
    public void setUp() {
        Assertion.reset();
        Assertion.addListener(_listener);
    }

    @After
    public void tearDown() {
        Assertion.reset();
    }


    @Test
    public void testEqualsObjectTrue() throws Exception {

        final String expectedMessage = "these two strings are the same literal";
        Assertion.equals(expectedMessage, "greek", "greek");

        final AssertionEvent event = _listener.getLast();

        Assert.assertNotNull(event);
        Assert.assertEquals(expectedMessage, event.getMessage());
        Assert.assertEquals(true, event.getIsTrue());

    }



    @Test
    public void testMessageCannotBeNull() throws Exception {
        Assert.fail("test not implemented");
    }

    @Test
    public void testMessageCannotBeEmptyString() throws Exception {
        Assert.fail("test not implemented");
    }

    @Test
    public void testEqualsObjectFalse() throws Exception {

        final String expectedMessage = "these two strings are the same literal";
        Assertion.equals(expectedMessage, "greek", "Greek");

        final AssertionEvent event = _listener.getLast();

        Assert.assertNotNull(event);
        Assert.assertEquals(expectedMessage, event.getMessage());
        Assert.assertEquals(false, event.getIsTrue());
    }

    @Test
    public void testEqualsBooleanPrimitiveTrue() throws Exception {
        Assertion.equals("message", true, true);
        final AssertionEvent event = _listener.getLast();
        Assert.assertEquals(true, event.getIsTrue());
    }


    @Test
    public void testEqualsBooleanPrimitiveFalse() throws Exception {
        Assertion.equals("message", true, false);
        final AssertionEvent event = _listener.getLast();
        Assert.assertEquals(false, event.getIsTrue());
    }

    @Test
    public void testEqualsLongPrimitiveTrue() throws Exception {

        long alpha = Long.parseLong("12345");
        long beta = Long.parseLong("12345");

        Assertion.equals("12345", alpha, beta);
        final AssertionEvent event = _listener.getLast();
        Assert.assertEquals(true, event.getIsTrue());

    }

    @Test
    public void testEqualsLongPrimitiveFalse() throws Exception {

        long alpha = Long.parseLong("12345");
        long beta = Long.parseLong("12344");

        Assertion.equals("12345", alpha, beta);
        final AssertionEvent event = _listener.getLast();
        Assert.assertEquals(false, event.getIsTrue());

    }

    @Test
    public void testEqualsCharTrue() throws Exception {

        char x = 'x';
        char y = 'x';

        Assertion.equals("char true", x,y);

        final AssertionEvent event = _listener.getLast();
        Assert.assertEquals(true, event.getIsTrue());

    }

    @Test
    public void testEqualsCharFalse() throws Exception {

        char x = 'x';
        char y = 'y';

        Assertion.equals("char true", x,y);

        final AssertionEvent event = _listener.getLast();
        Assert.assertEquals(false, event.getIsTrue());

    }



    public void testEqualsFloatPrimitiveTrue() throws Exception {
        // todo - implement this
    }


    public void testEqualsFloatPrimitiveFalse() throws Exception {
        // todo - implement this
    }

    @Test
    public void testNullTrue() throws Exception {

        Assertion.isNull("This should be null", null);
        final AssertionEvent event = _listener.getLast();
        Assert.assertEquals(true, event.getIsTrue());

    }
    @Test
    public void testNullFalse() throws Exception {

        Assertion.isNull("This should be null", new Object());
        final AssertionEvent event = _listener.getLast();
        Assert.assertEquals(false, event.getIsTrue());

    }

    @Test
    public void testNotNullTrue() throws Exception {
        Assertion.notNull("message", new Object());
        final AssertionEvent event = _listener.getLast();
        Assert.assertEquals(true, event.getIsTrue());
    }

    @Test
    public void testNotNullFalse() throws Exception {
        Assertion.notNull("message", null);
        final AssertionEvent event = _listener.getLast();
        Assert.assertEquals(false, event.getIsTrue());
    }

    @Test
    public void testSameTrue() throws Exception {

        Object x = new Object();
        Object y = x;
        Assertion.same("passing the same object reference",x,y);
        final AssertionEvent event = _listener.getLast();
        Assert.assertEquals(true, event.getIsTrue());

    }

    @Test
    public void testSameFalse() throws Exception {

        Assertion.same("passing two different object reference", new Object(), new Object());

        final AssertionEvent event = _listener.getLast();
        Assert.assertEquals(false, event.getIsTrue());

    }

    @Test
    public void testSameNullNotNull() throws Exception {
        Assertion.same("dummy", new Object(), null);

        final AssertionEvent event = _listener.getLast();
        Assert.assertEquals(false, event.getIsTrue());
    }

    @Test
    public void testSameNullNull() throws Exception {
        Assertion.same("dummy", null, null);

        final AssertionEvent event = _listener.getLast();
        Assert.assertEquals(true, event.getIsTrue());
    }

    @Test
    public void testSameNotNullNull() throws Exception {
        Assertion.same("dummy", null, new Object());

        final AssertionEvent event = _listener.getLast();
        Assert.assertEquals(false, event.getIsTrue());
    }

    @Test
    public void testNotSameTrue() throws Exception {
        Assertion.notSame("passing two different object reference", new Object(), new Object());

        final AssertionEvent event = _listener.getLast();
        Assert.assertEquals(true, event.getIsTrue());
    }

    @Test
    public void testNotSameFalse() throws Exception {
        Object x = new Object();
        Object y = x;
        Assertion.notSame("passing the same object reference",x,y);
        final AssertionEvent event = _listener.getLast();
        Assert.assertEquals(false, event.getIsTrue());
    }


    public void testEqualsDoublePrimitiveTrue() throws Exception {
        // todo - implement this
    }

    public void testEqualsDoublePrimitiveFalse() throws Exception {
        // todo - implement this
    }

    // todo - double test within margin

}
