package net.forje.common.types;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 */
public class DollarTest {

    private RoundingMode _roundingMode;

    @Test
    public void testImplementsComparable() throws Exception {
        Assert.assertEquals(true, Comparable.class.isAssignableFrom(Dollar.class));
    }

    @Test
    public void testEqualsTrue() throws Exception {
        Assert.assertEquals(true, Dollar.ONE.equals(Dollar.ONE));
    }


    @Test
    public void testEqualsFalse() throws Exception {
        Assert.assertEquals(false, Dollar.ZERO.equals(Dollar.ONE));
    }

    @Test
    public void testAdd() throws Exception {
        Dollar sum = Dollar.ZERO.add(Dollar.ONE);
        Assert.assertEquals(Dollar.ONE, sum);
    }

    @Test
    public void testSubtract() throws Exception {

        final Dollar alpha = Dollar.valueOf("9.99");
        final Dollar beta = Dollar.valueOf("3.33");

        final Dollar expected = Dollar.valueOf("6.66");

        Assert.assertEquals(expected, alpha.subtract(beta));

    }

    @Test
    public void testSubtractNegativeResult() throws Exception {

        final Dollar alpha = Dollar.valueOf("9.99");
        final Dollar beta = Dollar.valueOf("3.33");

        final Dollar expected = Dollar.valueOf("-6.66");

        Assert.assertEquals(expected, beta.subtract(alpha));

    }

    @Test
    public void testCompareLessThan() throws Exception {
        Assert.assertEquals(true, Dollar.ZERO.compareTo(Dollar.ONE) < 0);
    }

    @Test
    public void testCompareGreaterThan() throws Exception {
        Assert.assertEquals(true, Dollar.ONE.compareTo(Dollar.ZERO) > 0);
    }

    @Test
    public void testCompareEquals() throws Exception {
        Assert.assertEquals(0, Dollar.ZERO.compareTo(Dollar.ZERO));
    }

    @Test
    public void testZeroConstant() throws Exception {
        final Dollar zero = Dollar.valueOf("0.00");
        Assert.assertEquals(zero, Dollar.ZERO);
    }

    @Test
    public void testOneConstant() throws Exception {
        final Dollar zero = Dollar.valueOf("1.00");
        Assert.assertEquals(zero, Dollar.ONE);
    }

    @Test
    public void testBigDecimalValueScale2() throws Exception {
        Assert.assertEquals(2, Dollar.ONE.bigDecimalValue().scale());
    }

    @Test
    public void testAddImmutable() throws Exception {

        final Dollar first = Dollar.valueOf("9.99");
        final Dollar reference = Dollar.valueOf("9.99");
        final Dollar second = Dollar.valueOf("0.01");

        Assert.assertNotSame(first, reference);
        Assert.assertEquals(first, reference);

        final Dollar sum = first.add(second);

        Assert.assertEquals(first, reference);

        Assert.assertNotSame(sum, first);

    }

    @Test
    public void testSubtractImmutable() throws Exception {

        final Dollar alpha = Dollar.valueOf("119.33");
        final Dollar beta = Dollar.valueOf("2.33");

        final Dollar result = alpha.subtract(beta);

        Assert.assertEquals(Dollar.valueOf("119.33"), alpha);
        Assert.assertEquals(Dollar.valueOf("2.33"), beta);

    }

    @Test
    public void testMultiplyInt() throws Exception {

        // this test verified that autoboxing a primitive int
        // resolved to the valueOf(Number) signature.

        final Dollar value = Dollar.valueOf("6.46");
        final int multiplier = 2;
        final Dollar result = value.multiply(multiplier);

        final Dollar expected = Dollar.valueOf("12.92");

        Assert.assertEquals(expected, result);

    }


    @Test
    public void testMultiplyByNumber() throws Exception {

        final Dollar value = Dollar.valueOf("10.00");

        Dollar result = value.multiply(new Double(2));

        final Dollar expected = Dollar.valueOf("20.00");

        Assert.assertEquals(expected, result);

    }


    @Test
    public void testMultiplyFractionUsesDefaultRoundingMode() throws Exception {

        final Dollar value = Dollar.valueOf("10.00");

        Dollar.setDefaultRoundingMode(RoundingMode.HALF_DOWN);
        final Dollar halfDown = value.multiply(2.0005);
        Assert.assertEquals("half down", Dollar.valueOf("20.00"), halfDown);

        Dollar.setDefaultRoundingMode(RoundingMode.HALF_UP);
        final Dollar halfUp = value.multiply(2.0005);
        Assert.assertEquals("half up",Dollar.valueOf("20.01"), halfUp);

    }


    @Test
    public void testMultiplyFractionSpecifyRoundingMode() throws Exception {

        final Dollar value = Dollar.valueOf("10.00");

        Dollar.setDefaultRoundingMode(RoundingMode.HALF_DOWN);

        final Dollar halfUp = value.multiply(2.0005, RoundingMode.HALF_UP);
        Assert.assertEquals("half up",Dollar.valueOf("20.01"), halfUp);
    }

    @Test
    public void testMultiplyImmutable() throws Exception {

        final Dollar alpha = Dollar.valueOf("2.50");
        final Dollar result = alpha.multiply(2);

        final Dollar expected = Dollar.valueOf("5.00");

        Assert.assertEquals(Dollar.valueOf("2.50"), alpha);

    }

    @Test
    public void testDivideDefaultRoundingMode() throws Exception {


        Dollar.setDefaultRoundingMode(RoundingMode.HALF_DOWN);

        final Dollar value = Dollar.valueOf("5.11");
        Dollar actual = value.divide(2);
        final Dollar expected = Dollar.valueOf("2.55");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testDivideHalfDown() throws Exception {

        final Dollar value = Dollar.valueOf("5.11");
        Dollar actual = value.divide(2, RoundingMode.HALF_DOWN);
        final Dollar expected = Dollar.valueOf("2.55");
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void testDivideHalfUp() throws Exception {

        final Dollar value = Dollar.valueOf("5.11");
        Dollar actual = value.divide(2, RoundingMode.HALF_UP);
        final Dollar expected = Dollar.valueOf("2.56");
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void testIndivisible() throws Exception {
        // divide 0.02 by 3
        // not sure what it should do
        // probably throw an IllegalStateException
    }

    @Test
    public void testValueOfBigDecimal() throws Exception {
        final String stringValue = "99.95";
        final Dollar actual = Dollar.valueOf(new BigDecimal(stringValue));
        final Dollar expected = Dollar.valueOf(stringValue);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testValueOfBigDecimalUsesRoundingMode() throws Exception {



        // this has too many decimal points
        final BigDecimal value = new BigDecimal("9.115");

        Dollar.setDefaultRoundingMode(RoundingMode.HALF_UP);
        Dollar halfUp = Dollar.valueOf(value);
        Assert.assertEquals(Dollar.valueOf("9.12"), halfUp);

        Dollar.setDefaultRoundingMode(RoundingMode.HALF_DOWN);
        Dollar halfDown = Dollar.valueOf(value);
        Assert.assertEquals(Dollar.valueOf("9.11"), halfDown);



    }

    @Before
    public void beforeTest() {
        _roundingMode = Dollar.getDefaultRoundingMode();
    }

    @After
    public void afterTest() {
        Dollar.setDefaultRoundingMode(_roundingMode);
    }

    @Test
    public void testValueOfBigDecimalWithRoundingMode() throws Exception {

        Dollar.setDefaultRoundingMode(RoundingMode.HALF_UP);
        final BigDecimal input = new BigDecimal("3.335");
        final Dollar value = Dollar.valueOf(input, RoundingMode.HALF_DOWN);
        Assert.assertEquals(Dollar.valueOf("3.33"), value);

    }

    @Test
    public void testValueOfStringWholeDollar() throws Exception {

        Dollar result = Dollar.valueOf("1");
        Assert.assertEquals(Dollar.ONE, result);

    }

    @Test
    public void testValueOfStringFractionalDollar() throws Exception {

        Dollar oneDollars = Dollar.valueOf("1");
        Dollar nintyNineCents = Dollar.valueOf("0.99");
        Dollar nintyEightCents = Dollar.valueOf("0.98");
        Assert.assertEquals(true, nintyNineCents.compareTo(oneDollars) < 0);
        Assert.assertEquals(true, nintyNineCents.compareTo(nintyEightCents) > 0);

    }

    @Test
    public void testHashCodeEqualsTrue() throws Exception {
        Assert.assertEquals(true, Dollar.valueOf("19.95").hashCode() == Dollar.valueOf("19.95").hashCode());
    }

    @Test
    public void testHashCodeEqualsFalse() throws Exception {
        Assert.assertEquals(true, Dollar.valueOf("12.34").hashCode() != Dollar.valueOf("23.45").hashCode());
    }

    @Test
    public void testToString() throws Exception {

        final String stringValue = "8.85";
        final Dollar test = Dollar.valueOf(stringValue);
        Assert.assertEquals(stringValue, test.toString());

        Assert.assertEquals("20.00", Dollar.valueOf("20.00").toString());

    }

    @Test
    public void testToStringNegative() throws Exception {

        final String stringValue = "-8.85";
        final Dollar test = Dollar.valueOf(stringValue);
        Assert.assertEquals(stringValue, test.toString());

    }

}
