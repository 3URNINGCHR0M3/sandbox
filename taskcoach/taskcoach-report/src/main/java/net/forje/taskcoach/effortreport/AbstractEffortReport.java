package net.forje.taskcoach.effortreport;

import net.forje.taskcoach.jaxb.Tasks;

import java.io.File;

public abstract class AbstractEffortReport implements EffortReport {

    private final TaskVisitor _visitor;

    protected AbstractEffortReport(final TaskVisitor visitor) {
        _visitor = visitor;
    }

    public void generate(final File inputFile) throws Exception {

        FileMarshaller unmarshaller = new FileMarshaller();
        Tasks tasks = unmarshaller.process(inputFile);

        TaskReportIterator iterator = new TaskReportIterator(tasks);
        iterator.iterate(_visitor);

    }

}
