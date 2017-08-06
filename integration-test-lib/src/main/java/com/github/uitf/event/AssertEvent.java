package com.github.uitf.event;

import java.util.EventObject;

/**
 *
 */
public class AssertEvent extends EventObject {

    private final boolean _isTrue;
    private final String _message;

    public AssertEvent(final Object source, final boolean isTrue, final String message) {
        super(source);

        if (message == null || message.trim().isEmpty()) {
            throw new IllegalArgumentException("Message cannot be null or empty String.");
        }

        _isTrue = isTrue;
        _message = message;
    }

    public AssertEvent(final Object source, final String message) {
        this (source, false, message);
    }

    public boolean getIsTrue() {
        return _isTrue;
    }

    public String getMessage() {
        return _message;
    }


    @Override
    public String toString() {
        return _message+ ": " + _isTrue;
    }

}
