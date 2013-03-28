package net.forje.taskcoach.effortreport;

import net.forje.taskcoach.jaxb.EffortType;

import java.util.Date;

public class DateRangeMatchingTaskVisitor
        extends AbstractTaskVisitor {

    private final Date _startDate;
    private final Date _endDate;

    public DateRangeMatchingTaskVisitor(final Date startDate,
                                        final Date endDate) {

        if (startDate == null) {
            throw new IllegalArgumentException("start date can not be null");
        }

        if (endDate == null) {
            throw new IllegalArgumentException("end date can not be null");
        }

        _startDate = startDate;
        _endDate = endDate;

    }

    @Override
    protected boolean isMatch(final EffortType effort) {

        final String startValue = effort.getStart().substring(0, 10);

        boolean match = false;

        try {
            final Date effortStart = DATE_FORMATTER.parse(startValue);
            match = (effortStart.after(_startDate) || effortStart.equals(_startDate));

        } catch (Exception e) {
            e.printStackTrace();
            match = false;
        }

        if (match) {
            final String description = effort.getDescription();
//            System.out.println(description);
        }

        return match;

    }

}
