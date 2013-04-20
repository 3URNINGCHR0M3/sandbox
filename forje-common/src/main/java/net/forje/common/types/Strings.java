package net.forje.common.types;

/**
 * Utility methods for working with String objects
 */
public class Strings {

    /**
     * Determines if the String object passed in is null or an empty string.
     *
     * @param s the String object to evaluate
     * @return true if the String object is null or empty String
     */
    public static boolean isEmpty(final String s) {

        final boolean isStringNull = s == null;

        if (isStringNull) {
            return true;
        }

        return (s.trim().length() == 0);

    }

}
