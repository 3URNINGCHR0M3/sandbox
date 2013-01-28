package net.forje.taskcoach.effortreport;

import net.forje.taskcoach.types.Strings;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Properties;

public class DescriptionParser extends Properties {


    public static void loadDescription(final String description, final Properties properties) {

        if (Strings.isEmpty(description)) {
            return;
        }

        final ByteArrayInputStream inputStream = new ByteArrayInputStream(description.getBytes());

        try {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new IllegalArgumentException("unable to parse description.");
        }
    }


    public DescriptionParser(final String description) {
        loadDescription(description, this);
    }

    public boolean isValid() {
        return !Strings.isEmpty(getClient())
                && !Strings.isEmpty(getProject())
                && !Strings.isEmpty(getProject())
                && !Strings.isEmpty(getPhase())
                && !Strings.isEmpty(getActivity());
    }

    public String getClient() {

        return safeGetProperty("client");
    }

    private String safeGetProperty(final String key) {
        String s = (String) get(key);
        if (s == null) {
            s = "";
        }
        return s;
    }

    public String getProject() {
        return safeGetProperty("project");
    }

    public String getPhase() {
        return safeGetProperty("phase");
    }

    public String getActivity() {
        return safeGetProperty("activity");
    }

    public String getTask() {
        return safeGetProperty("task");
    }

    public String getDetails() {
        return safeGetProperty("details");
    }

}

