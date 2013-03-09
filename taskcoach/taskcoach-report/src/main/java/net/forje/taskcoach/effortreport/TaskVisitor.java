package net.forje.taskcoach.effortreport;

import net.forje.taskcoach.jaxb.EffortType;
import net.forje.taskcoach.jaxb.TaskType;

public interface TaskVisitor {
    
    void enterTask(TaskType task);

    void leaveTask(TaskType task);

    void processEffort(EffortType effort);
}
