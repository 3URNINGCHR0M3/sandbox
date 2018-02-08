package org.forje.gurps;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Responsible for creating new instances of the Randomizer interface.  Instances manage their own legal range.
 * Once created, the parseMuscleBased will return random values within that range.
 */
public class RandomizerFactory {

    private static final String REGEX_DEF = "([1-9]+[0-9]*)d([1-9]+[0-9]*)";
    private static final Pattern REGEX_PATTERN = Pattern.compile(REGEX_DEF);

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

    /**
     * Builds and returns a Randomizer equivialant to the String passed, being of the form nds, where
     * n is the number of dice and s is the number of sides on the dice.  For example, for a String "3d6" the
     * object returned will be a Randomizer returning values between 3 and 18, inclusive.
     *
     * @param s a String of the form nds
     *
     * @return a Randomizer matching the n dice of s sides String provided.
     */
    public static Randomizer fromString(final String s) {

        final Matcher matcher = REGEX_PATTERN.matcher(s);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("[" + s + "] is not a valid string.");
        }

        final int count = Integer.parseInt(matcher.group(1));
        final int sides = Integer.parseInt(matcher.group(2));

        return new CountSidesRandomizer(count, sides);

    }

    private static class DefaultRandomizer extends AbstractRandomizer {

        private final int _range;

        private DefaultRandomizer(int range) {
            _range = range;
            _min = 1;
            _max = range;
        }

        @Override
        public int produceValue() {
            return SHARED_RANDOM.nextInt(_range) + 1;
        }

    }

    private static class CountSidesRandomizer extends AbstractRandomizer {

        private final int _count;
        private final int _sides;
        private final Randomizer _randomizer;
        private final String _def;

        public CountSidesRandomizer(final int count,
                                    final int sides) {
            _count = count;
            _sides = sides;

            _def = _count+"d"+_sides;
            _min = _count;
            _max = _count * sides;

            _randomizer = RandomizerFactory.getRandomizer(_sides);

        }

        @Override
        public int produceValue() {

            int sum = 0;
            int count = 0;
            do {
                count++;
                sum = sum + _randomizer.produceValue();
            } while (count < _count);

            return sum;

        }

        @Override
        public String toString() {
            return _def;
        }

    }

    /**
     * Created by Brian on 3/29/15.
     */
    public abstract static class AbstractRandomizer
            implements Randomizer {

        protected int _min = 0;
        protected int _max = 0;

        public int getMin() {
            return _min;
        }

        public int getMax() {
            return _max;
        }
    }
}
