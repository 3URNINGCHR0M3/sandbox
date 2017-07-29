package org.forje.gurps.model;


import java.util.HashMap;
import java.util.Map;

/**
 * This class is intended to provide a mapping from a coded value to a more complex object,
 * typically an enumeration.
 */
public class GenericMap<T> {

    private final Map<String, T> STRING_TO_TYPE_MAP = new HashMap<>(10);

    protected final void add(String code, T value) {
        STRING_TO_TYPE_MAP.put(code, value);
    }

    /**
     * @exception IllegalArgumentException if the code is not valid
     */
    public T getValue(final String code) {

        final T t = (T) STRING_TO_TYPE_MAP.get(code);

        if (t == null) {
            throw new IllegalArgumentException("[" + code + "] does not match any known type");
        }
        return t;
    }

}
