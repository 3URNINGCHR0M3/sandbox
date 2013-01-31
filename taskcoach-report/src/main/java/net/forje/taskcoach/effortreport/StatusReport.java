package net.forje.taskcoach.effortreport;

import net.forje.taskcoach.types.Strings;

import java.io.PrintStream;
import java.util.*;

public class StatusReport extends AbstractEffortReport {

    private final Map<String,List> _releases = new TreeMap<String, List>();

    public StatusReport(final TaskVisitor visitor) {
        super(visitor);
    }

    public void print(final PrintStream out) {

        processAccumulatedData();
        dumpReleaseRelatedEffort(out);

    }

    private void dumpReleaseRelatedEffort(final PrintStream out) {

        final Set<Map.Entry<String, List>> entries = _releases.entrySet();

        PropertiesInterpreter interpreter;

        for (Iterator<Map.Entry<String, List>> releases = entries.iterator(); releases.hasNext(); ) {

            final Map.Entry<String, List> entry = (Map.Entry<String, List>) releases.next();
            final String release = entry.getKey();
            final List list = entry.getValue();

            out.println("Release " + release);



            for (Iterator iterator1 = list.iterator(); iterator1.hasNext(); ) {
                interpreter = (PropertiesInterpreter) iterator1.next();
                final String jiraKey = interpreter.getJIRAKey();
                if (!Strings.isEmpty(jiraKey)) {
                    out.println("   " + jiraKey);
                }

            }

            if (releases.hasNext()) {
                out.println();
            }

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

                final String release = interpreter.getRelease();
                List list = _releases.get(release);

                if (list == null) {
                    list = new ArrayList();
                    _releases.put(release, list);
                }

                list.add(interpreter);

            }

        }
    }

}
