package net.forje.taskcoach.effortreport;

import net.forje.taskcoach.types.Strings;

import java.io.PrintStream;
import java.util.*;

public class StatusReport extends AbstractEffortReport {

    private final Map<String, List> _releases = new TreeMap<String, List>();
    private final Map<String, List> _levelThreeIssues = new TreeMap<String, List>();
    private final Map<String, List> _certificationIssues = new TreeMap<String, List>();
    private final List _otherStuff = new ArrayList();


    public StatusReport(final TaskVisitor visitor) {
        super(visitor);
    }

    public void print(final PrintStream out) {

        processAccumulatedData();
        dumpReleaseRelatedEffort(out);
        out.println();
        dumpLevelThreeEffort(out);
        out.println();
        dumpCertificationEffort(out);
        out.println();
        dumpOtherStuff(out);

    }

    private void dumpOtherStuff(final PrintStream out) {

        out.println("*************************************************");
        out.println("**********         Other Stuff         **********");
        out.println("*************************************************");
        out.println();

        for (Iterator iterator = _otherStuff.iterator(); iterator.hasNext(); ) {
            Accumulator accumulator = (Accumulator) iterator.next();
            final String name = accumulator.getName();
            out.println(name);
            dumpEffortDetails(accumulator, out);
        }

    }

    private void dumpLevelThreeEffort(final PrintStream out) {

        out.println("*************************************************");
        out.println("**********         Level Three         **********");
        out.println("*************************************************");
        out.println();

        final Set<Map.Entry<String, List>> entries = _levelThreeIssues.entrySet();

        PropertiesInterpreter interpreter;
        Accumulator accumulator;
        Properties properties;

        for (Iterator<Map.Entry<String, List>> customers = entries.iterator(); customers.hasNext(); ) {

            final Map.Entry<String, List> entry = (Map.Entry<String, List>) customers.next();
            final String customer = entry.getKey();

            final List list = entry.getValue();
            if (list == null) {
                continue;
            }

            if (list.size() == 0) {
                continue;
            }

            out.println("Customer: " + customer);

            for (Iterator issues = list.iterator(); issues.hasNext(); ) {
                accumulator = (Accumulator) issues.next();
                properties = accumulator.getProperties();
                interpreter = new PropertiesInterpreter(properties);
                final String jiraKey = interpreter.getJIRAKey();
                if (!Strings.isEmpty(jiraKey)) {
                    out.print(jiraKey);

                    final String component = interpreter.getComponent();
                    if (!Strings.isEmpty(component)) {
                        out.print(" - " + component);
                    }

                    out.println();
                } else {
                    out.println(accumulator.getName());
                }

                dumpEffortDetails(accumulator, out);

            }

            if (customers.hasNext()) {
                out.println();
            }

        }

    }

    private void dumpCertificationEffort(final PrintStream out) {

        out.println("*************************************************");
        out.println("**********       Certifications        **********");
        out.println("*************************************************");

        out.println();

        final Set<Map.Entry<String, List>> entries = _certificationIssues.entrySet();

        PropertiesInterpreter interpreter;
        Accumulator accumulator;
        Properties properties;

        for (Iterator<Map.Entry<String, List>> customers = entries.iterator(); customers.hasNext(); ) {

            final Map.Entry<String, List> entry = customers.next();
            final String customer = entry.getKey();

            final List list = entry.getValue();
            if (list == null) {
                continue;
            }

            if (list.size() == 0) {
                continue;
            }

            for (Iterator issues = list.iterator(); issues.hasNext(); ) {
                accumulator = (Accumulator) issues.next();
                properties = accumulator.getProperties();
                interpreter = new PropertiesInterpreter(properties);
                final String jiraKey = interpreter.getJIRAKey();
                if (!Strings.isEmpty(jiraKey)) {

                    final String component = interpreter.getComponent();
                    final String vendor = interpreter.getVendor();

                    StringBuilder builder = new StringBuilder(100);
                    builder.append(jiraKey);
                    builder.append(" - ");

                    if (!Strings.isEmpty(vendor)) {
                        builder.append(vendor);
                        builder.append(" (");
                        builder.append(customer);
                        builder.append(")");
                    } else {
                        builder.append(customer);
                    }

                    if (!Strings.isEmpty(component)) {
                        builder.append(" - ");
                        builder.append(component);
                    }

                    out.println(builder.toString());

                }

                dumpEffortDetails(accumulator, out);

            }

            if (customers.hasNext()) {
                out.println();
            }

        }

    }

    private void dumpReleaseRelatedEffort(final PrintStream out) {

        out.println("*************************************************");
        out.println("********** Development and Maintenance **********");
        out.println("*************************************************");
        out.println();

        final Set<Map.Entry<String, List>> entries = _releases.entrySet();

        PropertiesInterpreter interpreter;
        Accumulator accumulator;
        Properties properties;

        for (Iterator<Map.Entry<String, List>> releases = entries.iterator(); releases.hasNext(); ) {

            final Map.Entry<String, List> entry = (Map.Entry<String, List>) releases.next();
            final String release = entry.getKey();
            final List list = entry.getValue();

            out.println("Release " + release);

            for (Iterator accumulators = list.iterator(); accumulators.hasNext(); ) {

                accumulator = (Accumulator) accumulators.next();

                properties = accumulator.getProperties();
                interpreter = new PropertiesInterpreter(properties);

                final String jiraKey = interpreter.getJIRAKey();
                if (!Strings.isEmpty(jiraKey)) {
                    out.print(jiraKey);

                    final String component = interpreter.getComponent();
                    if (!Strings.isEmpty(component)) {
                        out.print(" - " + component);
                    }

                    out.println();


                } else {
                    String name = accumulator.getName();
                    out.println(name);
                }

                dumpEffortDetails(accumulator, out);

            }

            if (releases.hasNext()) {
                out.println();
            }

        }

    }

    private void dumpEffortDetails(final Accumulator accumulator, final PrintStream out) {
        final Iterator effortComments = accumulator.getEffortComments();
        while (effortComments.hasNext()) {
            String comment = (String) effortComments.next();

            if (Strings.isEmpty(comment)) {
                continue;
            }

            comment = comment.replaceAll("(\\r|\\n)", ";");
            out.println(" * " + comment);
        }
    }

    private void processAccumulatedData() {

        final Iterator accumulators = Accumulators.accumulators();

        Properties properties;
        PropertiesInterpreter interpreter;
        Accumulator accumulator;

        while (accumulators.hasNext()) {
            accumulator = (Accumulator) accumulators.next();
            properties = accumulator.getProperties();
            interpreter = new PropertiesInterpreter(properties);

            if (interpreter.isReleaseRelated()) {

                List list;
                final String release = interpreter.getRelease();

                if (Strings.isEmpty(release)) {
                    throw new RuntimeException("Found release related element w/o a release [" + accumulator.getName() + "]");
                }

                if (_releases.containsKey(release)) {
                    list = _releases.get(release);
                } else {
                    list = new ArrayList();
                    _releases.put(release, list);
                }

                list.add(accumulator);

                continue;

            }

            if (interpreter.isLevelThree()) {

                String customer = interpreter.getCustomer();
                if (Strings.isEmpty(customer)) {
                    customer = "Unknown";
                }

                List list = _levelThreeIssues.get(customer);
                if (list == null) {
                    list = new ArrayList();
                    _levelThreeIssues.put(customer, list);
                }

                list.add(accumulator);

                continue;

            }

            if (interpreter.isCertification()) {

                String customer = interpreter.getCustomer();
                if (Strings.isEmpty(customer)) {
                    customer = "Unknown";
                }

                List list = _certificationIssues.get(customer);
                if (list == null) {
                    list = new ArrayList();
                    _certificationIssues.put(customer, list);
                }

                list.add(accumulator);

                continue;

            }

            // if we got there it wasn't any of the previous categories, so we'll create an "other" bucket
            _otherStuff.add(accumulator);

        }

    }

}
