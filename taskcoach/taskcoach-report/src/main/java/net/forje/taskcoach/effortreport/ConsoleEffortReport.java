package net.forje.taskcoach.effortreport;

import net.forje.taskcoach.types.Duration;
import net.forje.taskcoach.types.DurationFormat;
import net.forje.taskcoach.types.Strings;

import java.io.File;
import java.io.PrintStream;
import java.util.Date;
import java.util.Iterator;

public class ConsoleEffortReport extends AbstractEffortReport {

    public ConsoleEffortReport(final TaskVisitor visitor) {
        super(visitor);
    }

    public void print(PrintStream out) {

        final DurationFormat format = DurationFormat.getDecimalFormat();

        final Iterator iterator = Accumulators.accumulators();

        while (iterator.hasNext()) {

            Accumulator accumulator = (Accumulator) iterator.next();

            final String name = accumulator.getName();
            final int value = accumulator.getValue();

            final Duration duration = new Duration(value);

            System.out.println(name + " : " + format.format(duration));
            final String description = accumulator.getDescription();

            if (!Strings.isEmpty(description)) {
                System.out.println(description);

                final DescriptionParser descriptionParser = new DescriptionParser(description);
                if (!descriptionParser.isValid()) {
                    System.out.println("WHOA Dragon!");
                }

            }

        }

    }

}
