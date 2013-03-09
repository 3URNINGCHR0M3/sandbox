package net.forje.taskcoach.effortreport;

import org.junit.Assert;
import org.junit.Test;

import javax.print.DocFlavor;
import java.util.Properties;

public class PropertyStackTest {

    private final static String ALPHA = "alpha";
    private final static String BETA = "beta";
    private final static String DELTA = "delta";
    private static final String ONE = "one";
    private static final String TWO = "two";
    private static final String ZERO = "zero";
    private static final String FORCE = "force";

    @org.junit.Test
    public void testConstructor() throws Exception {
        new PropertyStack();
    }

    @Test
    public void testPush() throws Exception {
        final Properties properties = new Properties();
        final PropertyStack propertyStack = new PropertyStack();
        propertyStack.push(properties);

    }

    @Test
    public void testPushAndScrapeWithJustOneEntry() throws Exception {

        final Properties properties = new Properties();
        properties.setProperty(ALPHA, ONE);
        properties.setProperty(BETA, "two");
        final PropertyStack propertyStack = new PropertyStack();
        propertyStack.push(properties);

        Properties scraped = propertyStack.scrape();

        Assert.assertEquals(properties.getProperty(ALPHA), scraped.getProperty(ALPHA));
        Assert.assertEquals(properties.getProperty(BETA), scraped.getProperty(BETA));

    }

    @Test
    public void testDoublePushIntersect() throws Exception {

        final PropertyStack propertyStack = new PropertyStack();

        final Properties first = new Properties();
        first.setProperty(ALPHA, ONE);
        first.setProperty(BETA, TWO);

        propertyStack.push(first);

        final Properties second = new Properties();
        second.setProperty(BETA, ZERO);

        propertyStack.push(second);

        final Properties scraped = propertyStack.scrape();

        Assert.assertEquals(ONE, scraped.getProperty(ALPHA));
        Assert.assertEquals(ZERO, scraped.getProperty(BETA));

    }

    @Test
    public void testDoublePushUnion() throws Exception {


        final PropertyStack propertyStack = new PropertyStack();

        final Properties first = new Properties();
        first.setProperty(ALPHA, ONE);
        first.setProperty(DELTA, FORCE);

        propertyStack.push(first);

        final Properties second = new Properties();
        second.setProperty(ALPHA, ZERO);
        second.setProperty(BETA, TWO);

        propertyStack.push(second);

        final Properties scraped = propertyStack.scrape();

        Assert.assertEquals(ZERO, scraped.getProperty(ALPHA));
        Assert.assertEquals(TWO, scraped.getProperty(BETA));
        Assert.assertEquals(FORCE, scraped.getProperty(DELTA));

    }

    @Test
    public void testPopAndScrape() throws Exception {

        final PropertyStack propertyStack = new PropertyStack();

        final Properties first = new Properties();
        first.setProperty(ALPHA, ONE);
        first.setProperty(DELTA, FORCE);

        propertyStack.push(first);

        final Properties second = new Properties();
        second.setProperty(ALPHA, ZERO);
        second.setProperty(BETA, TWO);

        propertyStack.push(second);

        propertyStack.pop();

        final Properties scraped = propertyStack.scrape();

        Assert.assertEquals(ONE, scraped.getProperty(ALPHA));
        Assert.assertEquals(FORCE, scraped.getProperty(DELTA));
        Assert.assertNull(scraped.getProperty(BETA));

    }

}
