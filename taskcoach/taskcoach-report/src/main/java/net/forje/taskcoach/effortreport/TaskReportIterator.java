package net.forje.taskcoach.effortreport;

import net.forje.taskcoach.jaxb.EffortType;
import net.forje.taskcoach.jaxb.TaskType;
import net.forje.taskcoach.jaxb.Tasks;

import java.util.Iterator;
import java.util.List;

/**
 * This class is responsible for iterating over the JAXB object and invoking the visitor.
 */
public class TaskReportIterator {

    private final Tasks _tasksRoot;

    public TaskReportIterator(final Tasks tasksRoot) {
        _tasksRoot = tasksRoot;
    }

    public void iterate(final TaskVisitor visitor) throws Exception {

        final List<TaskType> taskList = _tasksRoot.getTask();

        processTasks(visitor, taskList);

    }

    private void processTasks(final TaskVisitor visitor,
                              final List<TaskType> taskList) {

        for (Iterator<TaskType> taskTypeIterator = taskList.iterator(); taskTypeIterator.hasNext(); ) {
            TaskType task = taskTypeIterator.next();
            processTask(visitor, task);
        }

    }

    private void processTask(final TaskVisitor visitor,
                             final TaskType task) {

        visitor.enteringTask(task);
        visitor.enterTask(task);

        final List<EffortType> effortList = task.getEffort();
        processEfforts(visitor, effortList);

        final List<TaskType> taskList = task.getTask();
        processTasks(visitor, taskList);

        visitor.leavingTask(task);
        visitor.leaveTask(task);

    }

    private void processEfforts(final TaskVisitor visitor,
                                final List<EffortType> effortList) {

        for (Iterator<EffortType> effortTypeIterator = effortList.iterator(); effortTypeIterator.hasNext(); ) {
            EffortType effort = effortTypeIterator.next();
            visitor.processEffort(effort);
        }

    }

}
