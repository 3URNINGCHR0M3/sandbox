package org.forje.gurps;

/**
 * This interface stands in for a physical die.  Since there is no real rolling of dice, there is no need to perpetuate
 * the naming convention.  Implementations will return an integer value.  The implementation will be responsible for
 * knowing the legal range. Each invocation of this operation is expected to produce a pseudo-random value, not a static
 * value which was arrived at randomly.
 */
public interface Randomizer {

    public int produceValue();

    public int getMin();

    public int getMax();
}
