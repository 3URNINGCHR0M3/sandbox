package net.forje.taskcoach.effortreport;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Accumulators {

    private static Map<String, Accumulator> ACCUMULATORS = new HashMap<String, Accumulator>() ;

    public static synchronized Accumulator get(final String name) {

        Accumulator accumulator = null;

        accumulator = ACCUMULATORS.get(name);

        if (accumulator == null) {
            accumulator = new Accumulator(name);
            ACCUMULATORS.put(name,accumulator);
        }

        return accumulator;

    }

    public static Iterator accumulators() {
        return ACCUMULATORS.values().iterator();
    }

}
