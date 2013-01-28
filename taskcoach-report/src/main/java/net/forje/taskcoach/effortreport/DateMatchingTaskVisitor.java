package net.forje.taskcoach.effortreport;


import net.forje.taskcoach.jaxb.EffortType;
import net.forje.taskcoach.jaxb.TaskType;
import net.forje.taskcoach.types.Strings;

import java.awt.datatransfer.StringSelection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Stack;

public class DateMatchingTaskVisitor
        extends AbstractTaskVisitor {

    private final Date _reportDate;

    public DateMatchingTaskVisitor(Date reportDate) {
        _reportDate = reportDate;
    }

    @Override
    protected boolean isMatch(final EffortType effort) {
        
        final String startValue = effort.getStart();
        final String stopValue = effort.getStop();

        final String s1 = startValue.substring(0, 10);

        boolean isReportDate = false;
        try {
            final Date startDate = DATE_FORMATTER.parse(s1);
            isReportDate = DATE_FORMATTER.format(startDate).equals(DATE_FORMATTER.format(_reportDate));
        } catch (ParseException e) {
            System.out.println("Could not parse date value [" + s1 + "]");
        }

        return isReportDate;

    }
    
}
