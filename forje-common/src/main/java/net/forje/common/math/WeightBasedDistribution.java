package net.forje.common.math;

import net.forje.common.types.Strings;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.*;

/**
 * Devides and distributes a decimal value based on a data set of decimal values.
 */
public class WeightBasedDistribution {

    private final int _scale;

    private final int _calculationScale;

    private final RoundingMode _roundingMode;

    private BigDecimal _total = BigDecimal.ZERO;

    private final Map<String, BigDecimal> _data = new HashMap<>();

    private boolean _locked = false;

    /**
     * Create an instance with the specified scale and RoundingMode.
     *
     * @param scale
     * @param roundingMode
     *
     */
    public WeightBasedDistribution(final int scale, final RoundingMode roundingMode) {
        _scale = scale;
        _roundingMode = roundingMode;
        _calculationScale = _scale + 1;
        _total.setScale(_scale);
    }

    /**
     * Create an instance with a default scale of 2 and RoundingMode.HALF_UP.
     */
    public WeightBasedDistribution() {
        this(2, RoundingMode.HALF_UP);
    }

    @Override
    public String toString() {
        return "WeightBasedDistribution - Scale [" + _scale +
                "] ; RoundingMode [" + _roundingMode +
                "]";
    }

    public void addValue(final String name,
                         final BigDecimal value) {

        if (_locked) {
            throw new IllegalStateException();
        }

        if (Strings.isEmpty(name)) {
            throw new IllegalArgumentException();
        }

        if (value == null) {
            throw new IllegalArgumentException();
        }

        if (_data.containsKey(name)) {
            throw new IllegalArgumentException("Name [" + name + "] already exists in the data set.");
        }

        final BigDecimal standardForm = value.setScale(_scale, _roundingMode);
        _data.put(name, standardForm);
        _total = _total.add(standardForm);

    }

    public Map<String, BigDecimal> distribute(final BigDecimal valueToDistribute) {

        _locked = true;

        final BigDecimal scaledDistribute = valueToDistribute.setScale(_scale);
        final BigDecimal totalSetValue = _total.setScale(_calculationScale);

        final HashMap<String, BigDecimal> resultSet = new HashMap<>(_data.size());

        final BigDecimal workingSum = getBigDecimal(scaledDistribute, totalSetValue, resultSet);

        final BigDecimal scaledSum = workingSum.setScale(_scale);

        final BigDecimal difference = scaledDistribute.subtract(scaledSum);

        if (!BigDecimal.ZERO.setScale(_scale).equals(difference)) {
            distributeDifference(difference, resultSet);
        }

        validateTotals(resultSet, scaledDistribute);

        return resultSet;

    }

    private BigDecimal getBigDecimal(final BigDecimal scaledDistribute,
                                     final BigDecimal totalSetValue,
                                     final HashMap<String, BigDecimal> resultSet) {

        BigDecimal workingSum = BigDecimal.ZERO;
        workingSum = workingSum.setScale(_scale);

        final Set<Map.Entry<String, BigDecimal>> entries = _data.entrySet();
        for (Iterator<Map.Entry<String, BigDecimal>> iterator = entries.iterator(); iterator.hasNext(); ) {

            Map.Entry<String, BigDecimal> next = iterator.next();

            // valeus from the map
            String name = next.getKey();
            BigDecimal sourceValue = next.getValue().setScale(_calculationScale);

            BigDecimal ratio = sourceValue.divide(totalSetValue, _calculationScale);

            BigDecimal resultValue = scaledDistribute.multiply(ratio);
            resultValue = resultValue.setScale(_scale, _roundingMode);

            workingSum = workingSum.add(resultValue);

            resultSet.put(name, resultValue.setScale(_scale, _roundingMode));

        }
        return workingSum;
    }

    private void validateTotals(final HashMap<String, BigDecimal> resultSet, final BigDecimal scaledSum) {

        BigDecimal workingSum = BigDecimal.ZERO.setScale(_scale);

        final Collection<BigDecimal> values = resultSet.values();
        for (Iterator<BigDecimal> iterator = values.iterator(); iterator.hasNext(); ) {
            BigDecimal next =  iterator.next();
            workingSum = workingSum.add(next);
        }

        if (!scaledSum.equals(workingSum)) {
            throw new IllegalStateException("Out of balance; expected [" + scaledSum + "] ; actual [" + workingSum + "]");
        }


    }

    private void distributeDifference(final BigDecimal valueToDistribute,
                                      final HashMap<String, BigDecimal> resultSet) {

        final BigDecimal unitToDistribute = BigDecimal.TEN.setScale(_scale).pow(_scale * -1, MathContext.DECIMAL32);
        final int steps = valueToDistribute.divide(unitToDistribute).intValue();

        int counter = 0;

        final Set<Map.Entry<String, BigDecimal>> entries = resultSet.entrySet();

        for (Iterator<Map.Entry<String, BigDecimal>> iterator = entries.iterator(); iterator.hasNext(); ) {

            Map.Entry<String, BigDecimal> next = iterator.next();
            final BigDecimal value = next.getValue();
            final BigDecimal adjustedValue = value.add(unitToDistribute).setScale(_scale);
            next.setValue(adjustedValue);

            counter++;
            if (counter == steps) {
                break;
            }

        }


    }

}
