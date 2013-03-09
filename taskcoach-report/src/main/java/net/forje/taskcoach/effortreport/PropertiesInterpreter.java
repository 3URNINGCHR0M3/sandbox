package net.forje.taskcoach.effortreport;

import net.forje.taskcoach.types.Strings;

import java.util.Properties;

public class PropertiesInterpreter {


    private final Properties _properties;

    public PropertiesInterpreter(final Properties properties) {
        _properties = properties;
    }

    public boolean isLevelThree() {

        String phase = getString(AutoHotKeyEffortReport.PHASE);
        boolean b = false;

        if (!Strings.isEmpty(phase)) {
            b = phase.contains(".APLEVEL3");
        }

        return b;
    }

    private String getString(final String key) {
        return (String) _properties.get(key);
    }

    public boolean isReleaseRelated() {

        final String client = getString(AutoHotKeyEffortReport.CLIENT);
        final String project = getString(AutoHotKeyEffortReport.PROJECT);

        return "00807424".equals(client) && (!"SEEC__.DEV".equalsIgnoreCase(project));

    }

    public String getRelease() {

        String release = getString(PropertyNames.Release.toString());

        return release;

    }

    public String getJIRAKey() {
        return getString(PropertyNames.JiraKey.toString());
    }

    public String getCustomer() {
        return getString(PropertyNames.Customer.toString());
    }

    public boolean isCertification() {
        return "CERTIFTEST".equals(getString(PropertyNames.Activity.toString()));
    }

    public String getVendor() {
        return getString(PropertyNames.Vendor.toString());
    }


    public String getComponent() {
        return getString(PropertyNames.Component.toString());
    }



}
