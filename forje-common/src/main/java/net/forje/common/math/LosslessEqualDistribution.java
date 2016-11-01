package net.forje.common.math;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * This class will distribute the BigDecimal value provided into the specified set of values,
 * without losing a part of the original value.
 *
 * For example, simply dividing BigDecimal("3.33") by BigDecimal("2") could yield a number of
 * different values, based on the RoundingMode used.  Regardless, dividing an odd value by an
 * even would end with either 1.66 and 1.66 or 1.67 and 1.67.  But if you some those two values
 * The sum of those two pairs is not equal to 3.33.
 *
 * Instead, what we need is to make sure the full value of the original amount to be distributed
 * across the specified number of quantities.  Continue the example above, the desired amounts
 * are 1.66 and 1.67.
 */
public class LosslessEqualDistribution {

    private final BigDecimal _value;
    private final int _scale;
    private final RoundingMode _roundingMode;

    public LosslessEqualDistribution(final BigDecimal value,
                                     final int scale,
                                     final RoundingMode roundingMode) {
        _value = value;
        _scale = scale;
        _roundingMode = roundingMode;

    }

    public List<BigDecimal> divide(Integer count) {


        final List<BigDecimal> list = new ArrayList<>(count);

        final BigDecimal divisor = BigDecimal.valueOf(count);
        final BigDecimal basic = _value.divide(divisor, _roundingMode);

        BigDecimal workingSum = BigDecimal.ZERO;

        for (int i=0; i < count; i++) {
            final BigDecimal temp = new BigDecimal(basic.toPlainString());
            list.add(temp);
            workingSum = workingSum.add(temp);
        }

        final BigDecimal difference = _value.subtract(workingSum);

        if (difference.compareTo(BigDecimal.ZERO) == 0) {
            // nothing to do
        } else if (difference.compareTo(BigDecimal.ZERO) > 0) {

            final BigDecimal unitToDistribute = BigDecimal.TEN.setScale(_scale).pow(_scale * -1, MathContext.DECIMAL32);
            final int steps = difference.divide(unitToDistribute).intValue();

            for (int i=0; i < steps; i++) {
                final BigDecimal temp = list.get(i);
                list.set(i, temp.add(unitToDistribute));
            }

        } else {
            // difference is negative
            System.out.println("difference = " + difference);

            final BigDecimal unitToDistribute = BigDecimal.TEN.setScale(_scale).pow(_scale * -1, MathContext.DECIMAL32);
            System.out.println("unitToDistribute = " + unitToDistribute);
            final int steps = Math.abs(difference.divide(unitToDistribute).intValue());

            System.out.println("steps = " + steps);
            for (int i=0; i < steps; i++) {
                int j = list.size() - 1 - i;
                final BigDecimal temp = list.get(j);
                list.set(j, temp.add(unitToDistribute.multiply(new BigDecimal("-1"))));
            }
        }

        return list;

    }

}
