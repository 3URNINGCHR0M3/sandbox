package com.github.uitf.event;

import org.junit.Assert;
import org.junit.Test;

import java.util.EventListener;

/**
 *
 */
public class AssertionEventListenerTest {

    @Test
    public void testExtendsEventListener() throws Exception {
        final Class subject = AssertionEventListener.class;
        Assert.assertEquals(true, EventListener.class.isAssignableFrom(subject));

    }

}
