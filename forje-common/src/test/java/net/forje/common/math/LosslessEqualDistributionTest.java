package net.forje.common.math;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 *
 */
public class LosslessEqualDistributionTest {

    @Test
    public void testEqualDivision() throws Exception {

        final BigDecimal value = new BigDecimal("10.00");
        final BigDecimal expected = new BigDecimal("2.50");

        final LosslessEqualDistribution distribution = new LosslessEqualDistribution(value, 2, RoundingMode.HALF_EVEN);

        final List<BigDecimal> result = distribution.divide(4);

        Assert.assertEquals(4, result.size());
        Assert.assertEquals(expected, result.get(0));
        Assert.assertEquals(expected, result.get(1));
        Assert.assertEquals(expected, result.get(2));
        Assert.assertEquals(expected, result.get(3));

    }

    @Test
    public void testUnequalDistribution() throws Exception {

        final BigDecimal value = new BigDecimal("10.00");

        final LosslessEqualDistribution distribution = new LosslessEqualDistribution(value, 2, RoundingMode.HALF_DOWN);
        final List<BigDecimal> list = distribution.divide(3);

        Assert.assertEquals(new BigDecimal("3.34"), list.get(0));
        Assert.assertEquals(new BigDecimal("3.33"), list.get(1));
        Assert.assertEquals(new BigDecimal("3.33"), list.get(2));

    }

    @Test
    public void testMultipleModifiedValues() throws Exception {

        final BigDecimal value = new BigDecimal("10.01");

        final LosslessEqualDistribution distribution = new LosslessEqualDistribution(value, 2, RoundingMode.HALF_DOWN);
        final List<BigDecimal> list = distribution.divide(3);

        Assert.assertEquals("one", new BigDecimal("3.34"), list.get(0));
        Assert.assertEquals("two", new BigDecimal("3.34"), list.get(1));
        Assert.assertEquals("three", new BigDecimal("3.33"), list.get(2));

    }

}
