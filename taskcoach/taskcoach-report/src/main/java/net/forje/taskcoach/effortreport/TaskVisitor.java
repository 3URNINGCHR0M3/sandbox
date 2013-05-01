package net.forje.taskcoach.effortreport;

import net.forje.taskcoach.jaxb.EffortType;
import net.forje.taskcoach.jaxb.TaskType;

public interface TaskVisitor {

    /**
     * Called before visiting the task node
     */
    void enteringTask(TaskType task);

    void enterTask(TaskType task);

    /**
     * Called before leaving the task node
     */
    void leavingTask(TaskType task);

    /**
     * Called as leave each task node
     */
    void leaveTask(TaskType task);

    /**
     * Called on each effort node
     */
    void processEffort(EffortType effort);
}
