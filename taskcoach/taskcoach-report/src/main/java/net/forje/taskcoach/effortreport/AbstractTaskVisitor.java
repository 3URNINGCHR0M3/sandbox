package net.forje.taskcoach.effortreport;


import net.forje.taskcoach.jaxb.EffortType;
import net.forje.taskcoach.jaxb.TaskType;
import net.forje.taskcoach.types.Strings;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractTaskVisitor implements TaskVisitor {

    private static final String TASKCOACH_DATE_PATTERN = "yyyy-MM-dd";
    private static final String TASKCOACH_TIMESTAMP_PATTERN = "yyyy-MM-dd HH:mm:ss";
    protected static final DateFormat TIMESTAMP_FORMATTER = new SimpleDateFormat(TASKCOACH_TIMESTAMP_PATTERN);
    protected static final DateFormat DATE_FORMATTER = new SimpleDateFormat(TASKCOACH_DATE_PATTERN);

    private final Stack _tasks = new Stack();
    private TaskType _currentTask;
    private final PropertyStack _propertyStack = new PropertyStack();

    public final void enterTask(TaskType task) {
        _tasks.push(task);
        _currentTask = task;

        final String description = task.getDescription();
        final Properties properties = new Properties();

        DescriptionParser.loadDescription(description, properties);
        _propertyStack.push(properties);

    }

    public final void leaveTask(TaskType task) {
        _tasks.pop();
        _propertyStack.pop();
        _currentTask = null;
    }

    protected TaskType getCurrentTask() {
        return _currentTask;
    }

    public void processEffort(EffortType effort) {

        StringBuffer buffer = new StringBuffer();
        final int size = _tasks.size();
        String subject = null;

        for (int i = 0; i < size; i++) {

            if (i > 0) {
                buffer.append(" >> ");
            }

            TaskType task = (TaskType) _tasks.get(i);
            subject = task.getSubject();

            buffer.append(subject);

        }

        final String effortDescription = effort.getDescription();
        final String taskName = subject;


        boolean isMatch = isMatch(effort);


        if (isMatch) {

            try {

                final String taskPath = buffer.toString();

                final String startValue = effort.getStart();
                final String stopValue = effort.getStop();

                Date start = TIMESTAMP_FORMATTER.parse(startValue);
                Date stop = TIMESTAMP_FORMATTER.parse(stopValue);

                final int durationInMillis = (int) (stop.getTime() - start.getTime());

                final int minutes = (durationInMillis / 60000);

                final Accumulator accumulator = Accumulators.get(taskPath);
                accumulator.add(minutes);

                final Properties scrape = _propertyStack.scrape();
                final String details = scrape.getProperty("details");

                if (Strings.isEmpty(details)) {

                    final Pattern pattern = Pattern.compile("(ERX-\\d+).*");
                    final Matcher matcher = pattern.matcher(taskName);

                    if (matcher.matches()) {
                        matcher.find(0);
                        final String jiraKey = matcher.group(1);
                        scrape.setProperty("details", jiraKey);
                        scrape.setProperty(PropertyNames.JiraKey.toString(), jiraKey);

                    } else {
                        if (!Strings.isEmpty(effortDescription)) {
                            scrape.setProperty("details", effortDescription);
                        } else {
                            scrape.setProperty("details", taskName);
                        }
                    }
                }

                accumulator.setProperties(scrape);

                if (!Strings.isEmpty(effortDescription)) {
                    accumulator.addEffortComment(effortDescription);
                }

            } catch (ParseException
                    e) {
                e.printStackTrace();
            }

        }

    }

    protected abstract boolean isMatch(EffortType effort);


}
