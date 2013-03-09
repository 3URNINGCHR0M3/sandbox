package net.forje.taskcoach.effortreport;

import junit.framework.Assert;
import org.junit.Test;

import java.util.Properties;

public class PropertiesInterpreterTest {

    @Test
    public void testIsLevelThree() throws Exception {

        final Properties properties = new Properties();
        properties.setProperty(AutoHotKeyEffortReport.PHASE, "E.APLEVEL3");

        final PropertiesInterpreter interpreter = new PropertiesInterpreter(properties);
        Assert.assertEquals(true, interpreter.isLevelThree());

    }

    @Test
    public void testIsReleaseRelated() throws Exception {

        final Properties properties = new Properties();
        properties.setProperty(AutoHotKeyEffortReport.CLIENT, "00807424");

        final PropertiesInterpreter interpreter = new PropertiesInterpreter(properties);

        Assert.assertEquals(true, interpreter.isReleaseRelated());

    }

    @Test
    public void testGetRelease() throws Exception {
        final Properties properties = new Properties();
        properties.setProperty(PropertyNames.Release.toString(), "4.0");

        final PropertiesInterpreter interpreter = new PropertiesInterpreter(properties);
        Assert.assertEquals("4.0", interpreter.getRelease());
    }

    @Test
    public void testGetJiraKey() throws Exception {

        final Properties properties = new Properties();
        properties.setProperty(PropertyNames.JiraKey.toString(), "ERX-99999");

        final PropertiesInterpreter interpreter = new PropertiesInterpreter(properties);
        Assert.assertEquals("ERX-99999", interpreter.getJIRAKey());

    }

    @Test
    public void testIsCertification() throws Exception {
        final Properties properties = new Properties();
        properties.setProperty(PropertyNames.Activity.toString(), "CERTIFTEST");
        final PropertiesInterpreter interpreter = new PropertiesInterpreter(properties);
        Assert.assertEquals(true, interpreter.isCertification());

    }

    @Test
    public void testIsReleaseRelatedExcludesSEEC() throws Exception {

        final Properties properties = new Properties();
        properties.setProperty(AutoHotKeyEffortReport.CLIENT, "00807424");
        properties.setProperty(AutoHotKeyEffortReport.PROJECT, "SEEC__.DEV");

        final PropertiesInterpreter interpreter = new PropertiesInterpreter(properties);

        Assert.assertEquals(false, interpreter.isReleaseRelated());



    }

}
