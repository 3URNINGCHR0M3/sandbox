package net.forje.taskcoach.effortreport;

import net.forje.taskcoach.jaxb.EffortType;
import net.forje.taskcoach.jaxb.TaskType;
import net.forje.taskcoach.jaxb.Tasks;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractEffortReport implements EffortReport {

    private final TaskVisitor _visitor;

    protected AbstractEffortReport(final TaskVisitor visitor) {
        _visitor = visitor;
    }

    public void generate(final File inputFile) throws Exception {

        final InputStream inputStream = new FileInputStream(inputFile);

        JAXBContext jaxbContext = JAXBContext.newInstance("net.forje.taskcoach.jaxb");
        final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        final Tasks tasks = (Tasks) unmarshaller.unmarshal(inputStream);

        final List<TaskType> taskList = tasks.getTask();

        processTasks(_visitor, taskList);

    }

    private void processTasks(TaskVisitor visitor,
                              List<TaskType> taskList) {

        for (Iterator<TaskType> taskTypeIterator = taskList.iterator(); taskTypeIterator.hasNext(); ) {
            TaskType task = taskTypeIterator.next();
            processTask(visitor, task);
        }

    }

    private void processTask(TaskVisitor visitor, TaskType task) {

        visitor.enterTask(task);

        final List<EffortType> effortList = task.getEffort();
        processEfforts(visitor, effortList);

        final List<TaskType> taskList = task.getTask();
        processTasks(visitor, taskList);

        visitor.leaveTask(task);

    }

    private void processEfforts(TaskVisitor visitor, List<EffortType> effortList) {

        for (Iterator<EffortType> effortTypeIterator = effortList.iterator(); effortTypeIterator.hasNext(); ) {
            EffortType effort = effortTypeIterator.next();
            visitor.processEffort(effort);
        }

    }

}
