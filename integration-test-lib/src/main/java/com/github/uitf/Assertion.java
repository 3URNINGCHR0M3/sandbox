package com.github.uitf;

import com.github.uitf.event.AssertionEvent;
import com.github.uitf.event.AssertionEventListener;

/**
 *
 */
public class Assertion {

    private static AssertionEventListener _listener;

    public static void addListener(final AssertionEventListener listener) {

        if (listener == null) {
            throw new IllegalArgumentException();
        }

        if (_listener != null) {
            throw new IllegalStateException();
        }
        _listener = listener;
    }

    public static void reset() {
        _listener = null;
    }

    public static void equals(final String message,
                              final Object expected,
                              final Object actual) {

        final boolean result;

        if (expected == null && actual == null) {
            result = true;
        } else if (expected == null) {
            result = false;
        } else {
            result = expected.equals(actual);
        }

        _listener.assertion(new AssertionEvent(Assertion.class, result, message));


    }

    public static void isNull(final String message, final Object subject) {
        equals(message, null, subject);
    }

    public static void same(final String message, final Object expected, final Object actual) {
        _listener.assertion(new AssertionEvent(Assertion.class, expected == actual, message));
    }

    public static void notSame(final String message, final Object expected, final Object actual) {
        _listener.assertion(new AssertionEvent(Assertion.class, expected != actual, message));
    }

    public static void notNull(final String message, final Object subject) {
        _listener.assertion(new AssertionEvent(Assertion.class, subject != null, message));
    }

}
