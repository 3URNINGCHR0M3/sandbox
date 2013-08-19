package org.forje.gurps;

import java.util.Random;

/**
 * Responsible for creating new instances of the Randomizer interface.
 */
public class RandomizerFactory {

    private static Random SHARED_RANDOM = new Random(System.currentTimeMillis());

    /**
     * Returns an object implementing the Randomizer interface which will produce random values from 1 to range,
     * inclusive.
     *
     * @param range the max value on the range.
     */
    public static Randomizer getRandomizer(int range) {
        return new DefaultRandomizer(range);
    }

    private static class DefaultRandomizer implements Randomizer {

        private final int _range;

        private DefaultRandomizer(int range) {
            _range = range;
        }

        @Override
        public int produceValue() {
            return SHARED_RANDOM.nextInt(_range);
        }

    }

}
