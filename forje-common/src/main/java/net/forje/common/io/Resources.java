package net.forje.common.io;

import java.io.InputStream;
import java.net.URL;

/**
 * Utility methods for working with locating and loading resources from the classpath
 */
public class Resources {

    /**
     * Opens a resource identified from the classpath by the resourceName paramter.
     *
     * @param resourceName the path to resource to be opened.
     * @return InputStream to the resource
     * @throws IllegalArgumentException if the resource can not be located or opened
     */
    public static InputStream open(final String resourceName) {

        final ClassLoader classLoader = Resources.class.getClassLoader();

        return open(classLoader, resourceName);

    }

    /**
     * Opens a resource identified from the classpath by the resourceName paramter.
     *
     * @param resourceName the path to resource to be opened.
     * @param classLoader  the ClassLoader to use to access the resource
     * @return InputStream to the resource
     * @throws IllegalArgumentException if the resource can not be located or opened
     */
    public static InputStream open(final ClassLoader classLoader,
                                   final String resourceName) {

        final URL url = classLoader.getResource(resourceName);

        if (url == null) {
            throw new IllegalArgumentException("Could not locate resource [" + resourceName + "]");
        }

        final InputStream inputStream = classLoader.getResourceAsStream(resourceName);

        if (inputStream == null) {
            throw new IllegalStateException("Could not open resource [" + resourceName + "] located at [" + url.toString() + "]");
        }

        return inputStream;

    }

}
