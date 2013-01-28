package net.forje.taskcoach.effortreport;

import net.forje.taskcoach.types.Duration;
import net.forje.taskcoach.types.DurationFormat;

import java.io.PrintStream;
import java.util.Iterator;

public class DailyTotalReport
        extends AbstractEffortReport {

    public DailyTotalReport(final TaskVisitor visitor) {
        super(visitor);
    }

    public void print(final PrintStream out) {

        final DurationFormat durationFormat = DurationFormat.getDecimalFormat();

        int total = 0;

        final Iterator iterator = Accumulators.accumulators();
        Accumulator accumulator;

        while (iterator.hasNext()) {
            accumulator = (Accumulator) iterator.next();
            total = total + accumulator.getValue();
        }

        final Duration duration = new Duration(total);
        out.println("Total effort = " + durationFormat.format(duration) + " hours.");
        
    }

}
