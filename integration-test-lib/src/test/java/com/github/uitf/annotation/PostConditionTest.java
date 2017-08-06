package com.github.uitf.annotation;

import org.junit.Assert;
import org.junit.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 */
public class PostConditionTest {

    @Test
    public void testIsRunTimeRetention() throws Exception {
        final Class<com.github.uitf.annotation.PostCondition> subject = com.github.uitf.annotation.PostCondition.class;
        final Retention annotation = subject.getAnnotation(Retention.class);
        Assert.assertEquals(RetentionPolicy.RUNTIME, annotation.value());
    }

    @Test
    public void testTargetIsMethod() throws Exception {
        final Class<com.github.uitf.annotation.PostCondition> subject = com.github.uitf.annotation.PostCondition.class;
        final Target annotation = subject.getAnnotation(Target.class);
        final ElementType[] value = annotation.value();
        Assert.assertNotNull(value);
        Assert.assertEquals(1, value.length);
        Assert.assertEquals(ElementType.METHOD, value[0]);
    }

}
