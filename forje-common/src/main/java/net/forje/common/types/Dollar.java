package net.forje.common.types;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 *
 */
public class Dollar implements Comparable<Dollar> {

    public static Dollar ZERO = new Dollar(0);
    public static Dollar ONE = new Dollar(100);

    private static final int DEFAULT_SCALE = 2;
    public static BigDecimal ONE_HUNDRED = new BigDecimal("100.00").setScale(DEFAULT_SCALE);

    private String _toString;

    private static RoundingMode defaultRoundingMode = RoundingMode.HALF_UP;

    private int _cents;

    private Dollar(final int cents) {
        _cents = cents;
    }

    public static void setDefaultRoundingMode(final RoundingMode defaultRoundingMode) {
        Dollar.defaultRoundingMode = defaultRoundingMode;
    }

    public static RoundingMode getDefaultRoundingMode() {
        return defaultRoundingMode;
    }

    public Dollar add(final Dollar toAdd) {

        final int sum = this._cents + toAdd._cents;
        final Dollar dollar = new Dollar(sum);

        return dollar;

    }

    @Override
    public boolean equals(final Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Dollar dollar = (Dollar) o;

        return _cents == dollar._cents;

    }

    @Override
    public int hashCode() {
        return _cents;
    }

    @Override
    public int compareTo(final Dollar toCompare) {

        return this._cents - toCompare._cents;

    }


    public BigDecimal bigDecimalValue() {

        final BigDecimal value = new BigDecimal(_cents / 100);
        return value.setScale(DEFAULT_SCALE);

    }

    public static Dollar valueOf(final String value) {
        BigDecimal working = new BigDecimal(value).setScale(DEFAULT_SCALE);
        working = working.multiply(new BigDecimal("100"));
        final int cents  = working.intValue();

        return new Dollar(cents);

    }

    public Dollar subtract(final Dollar toSubtract) {

        final int result = this._cents - toSubtract._cents;

        return new Dollar(result);

    }

    public static Dollar valueOf(final BigDecimal input) {

       return valueOf(input, defaultRoundingMode);

    }

    public static Dollar valueOf(final BigDecimal input,
                                 final RoundingMode roundingMode) {

        final BigDecimal scaled = input.setScale(DEFAULT_SCALE, roundingMode);
        final BigDecimal centsBD = scaled.multiply(ONE_HUNDRED);
        final int cents = centsBD.intValue();

        return new Dollar(cents);

    }


    @Override
    public String toString() {

        if (_toString == null) {
            final BigDecimal bigDecimal = new BigDecimal(_cents);
            final BigDecimal result = bigDecimal.divide(ONE_HUNDRED);
            DecimalFormat myFormatter = new DecimalFormat("0.00");
            _toString = myFormatter.format(result);
        }

        return _toString;

    }

    public Dollar multiply(final Number multiplier) {

        final BigDecimal multiplierBd = new BigDecimal(multiplier.doubleValue());

        System.out.println("multiplierBd = " + multiplierBd);

        final BigDecimal currentValue = new BigDecimal(_cents).multiply(BigDecimal.TEN)
                .setScale(0, defaultRoundingMode);
        System.out.println("currentValue = " + currentValue);

        final BigDecimal result = multiplierBd.multiply(currentValue)
                .setScale(0, defaultRoundingMode);
        System.out.println("result = " + result);

        final BigDecimal working = result.divide(BigDecimal.TEN)
                .setScale(0, defaultRoundingMode);

        System.out.println("working = " + working);

        final int cents = working.intValue();
        System.out.println("cents = " + cents);

        return new Dollar(cents);

    }

}
