package com.github.uitf.event;

import java.util.EventListener;

/**
 *
 */
public interface AssertionEventListener extends EventListener {


    /**
     * Called when an assertion is executed by a test
     *
     * @param event details of the assertion
     */
    public void assertion(AssertionEvent event);

    /**
     * Called when an failure is produced by a test
     *
     * @param event details of the failure
     */
    public void failure(AssertionEvent event);

}
