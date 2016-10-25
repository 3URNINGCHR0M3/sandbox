package net.forje.common.math;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

/**
 *
 */
public class WeightBasedDistributionTest {

    @Test
    public void testDefaultConstructorToString() throws Exception {
        final WeightBasedDistribution distribution = new WeightBasedDistribution();
        String s = distribution.toString();
        Assert.assertEquals("WeightBasedDistribution - Scale [2] ; RoundingMode [HALF_UP]", s);
    }

    @Test
    public void testParamConstuctorToString() throws Exception {
        final WeightBasedDistribution distribution = new WeightBasedDistribution(3, RoundingMode.HALF_EVEN);
        String s = distribution.toString();
        Assert.assertEquals("WeightBasedDistribution - Scale [3] ; RoundingMode [HALF_EVEN]", s);
    }

    @Test
    public void testRoundingModeApplied() throws Exception {

        // 2, RoundHalf Up
        final WeightBasedDistribution distribution = new WeightBasedDistribution();
        distribution.addValue("alpha", new BigDecimal("3.745"));
        distribution.addValue("beta", new BigDecimal("2.252"));

        final Map results = distribution.distribute(new BigDecimal(10));

        Assert.assertEquals("alpha", new BigDecimal(6.25), results.get("alpha"));
        Assert.assertEquals("beta", new BigDecimal(3.75), results.get("beta"));

    }

    @Test
    public void testAddAfterResolveThrowsException() throws Exception {

        final WeightBasedDistribution distribution = new WeightBasedDistribution();
        distribution.addValue("alpha", new BigDecimal("5.00"));
        distribution.addValue("beta", new BigDecimal("5.00"));

        distribution.distribute(new BigDecimal(10));

        try {
            distribution.addValue("charlie", new BigDecimal("10.00"));
            Assert.fail("Expected an exception.");
        } catch (IllegalStateException e) {
            // todo test message
        }

    }

    @Test
    public void testAddNullNameThrowsException() throws Exception {
        final WeightBasedDistribution distribution = new WeightBasedDistribution();
        try {
            distribution.addValue(null, new BigDecimal("5.00"));
            Assert.fail("Expected an exception.");
        } catch (IllegalArgumentException e) {
            // todo test message
        }
    }

    @Test
    public void testAddNullValueThrowsException() throws Exception {
        final WeightBasedDistribution distribution = new WeightBasedDistribution();
        try {
            distribution.addValue("name", null);
            Assert.fail("Expected an exception.");
        } catch (IllegalArgumentException e) {
            // todo test message
        }
    }

    @Test
    public void testRequiresDistribution() throws Exception {

        final WeightBasedDistribution distribution = new WeightBasedDistribution();
        final BigDecimal value = new BigDecimal("10");

        distribution.addValue("alpha", value);
        distribution.addValue("beta", value);
        distribution.addValue("charlie", value);

        final Map<String, BigDecimal> result = distribution.distribute(new BigDecimal(20));

        Assert.assertEquals("alpha", new BigDecimal("6.67"), result.get("alpha"));
        Assert.assertEquals("beta", new BigDecimal("6.67"), result.get("beta"));
        Assert.assertEquals("charlie", new BigDecimal("6.66"), result.get("charlie"));

    }

    @Test
    public void testFourItemTest() throws Exception {

        final WeightBasedDistribution distribution = new WeightBasedDistribution(3, RoundingMode.HALF_DOWN);

        distribution.addValue("alpha", new BigDecimal("12.345"));
        distribution.addValue("beta", new BigDecimal("23.456"));
        distribution.addValue("charlie", new BigDecimal("34.567"));
        distribution.addValue("delta", new BigDecimal("45.678"));

        final Map<String, BigDecimal> result = distribution.distribute(new BigDecimal("35"));

        Assert.assertEquals("alpha", new BigDecimal("3.724"), result.get("alpha"));
        Assert.assertEquals("beta", new BigDecimal("7.073"), result.get("beta"));
        Assert.assertEquals("charlie", new BigDecimal("10.426"), result.get("charlie"));
        Assert.assertEquals("delta", new BigDecimal("13.777"), result.get("delta"));

    }

    @Test
    public void test() throws Exception {

        final WeightBasedDistribution distribution = new WeightBasedDistribution();
        distribution.addValue("one", new BigDecimal("27.95"));
        distribution.addValue("two", new BigDecimal("18.16"));
        distribution.addValue("three", new BigDecimal("34.12"));

        final Map<String, BigDecimal> result = distribution.distribute(new BigDecimal("30"));
        Assert.assertEquals("one", new BigDecimal("10.45"), result.get("one"));
        Assert.assertEquals("two", new BigDecimal("6.79"), result.get("two"));
        Assert.assertEquals("three", new BigDecimal("12.76"), result.get("three"));

    }

    @Test
    public void testDuplicateKeyThrowsIllegalArgExp() throws Exception {

        final WeightBasedDistribution distribution = new WeightBasedDistribution();

        distribution.addValue("one", new BigDecimal("9.99"));
        distribution.addValue("two", new BigDecimal("9.99"));

        try {
            distribution.addValue("one", new BigDecimal("9.99"));
            Assert.fail("Expected an exception.");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Name [one] already exists in the data set.", e.getMessage());
        }
    }

    @Test
    public void testEvenlyDistribute() throws Exception {

        final WeightBasedDistribution distribution = new WeightBasedDistribution();

        distribution.addValue("one", new BigDecimal("45.99"));
        distribution.addValue("two", new BigDecimal("45.99"));

        final Map<String, BigDecimal> result = distribution.distribute(new BigDecimal("2.00"));

        Assert.assertEquals("one", new BigDecimal("1.00"), result.get("one"));
        Assert.assertEquals("two", new BigDecimal("1.00"), result.get("two"));


    }

}
