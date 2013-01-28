package net.forje.taskcoach.types;

import java.math.BigDecimal;

/**
 *
 */
public abstract class DurationFormat {

    public abstract String format(Duration duration);

    private static final DurationFormat HOURS_MINUTES = new HoursMinutesFormat();
    private static final DurationFormat DECIMAL = new DecimalFormat();

    public static DurationFormat getHoursMinuteFormat() {
        return HOURS_MINUTES;
    }

    public static DurationFormat getDecimalFormat() {
        return DECIMAL;
    }


    private static class DecimalFormat extends DurationFormat {


        private static final BigDecimal MINUTES_IN_AN_HOUR = new BigDecimal(60);

        public String format(final Duration duration) {

            BigDecimal value = new BigDecimal(duration.getValue());
            final BigDecimal output = value.divide(MINUTES_IN_AN_HOUR);
            return output.toString();

        }

    }

    private static class HoursMinutesFormat extends DurationFormat {

        public String format(final Duration duration) {

            int hours = 0;
            int minutes = 0;

            final int value = duration.getValue();

            hours = value / 60;
            minutes = value - (hours * 60);

            final StringBuffer buffer = new StringBuffer(100);

            if (hours > 0) {
                buffer.append(hours);
                buffer.append(" hour");
                if (hours > 1) {
                    buffer.append("s");
                }
                buffer.append(" ");
            }


            buffer.append(minutes);
            buffer.append(" minutes");

            return buffer.toString();
        }


    }


}
