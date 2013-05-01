package net.forje.taskcoach.purge;

import net.forje.taskcoach.effortreport.*;
import net.forje.taskcoach.jaxb.EffortType;
import net.forje.taskcoach.jaxb.TaskType;
import net.forje.taskcoach.jaxb.Tasks;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Purger {

    public void processRequest(PurgeCLI.Parameters parameters) throws Exception {

        final FileMarshaller fileMarshaller = new FileMarshaller();
        final Tasks tasks = fileMarshaller.process(parameters.getFile());

        TaskReportIterator iterator = new TaskReportIterator(tasks);

        PurgeTaskVisitor purgeTaskVisitor = new PurgeTaskVisitor(parameters.getCutoffDate());

        iterator.iterate(purgeTaskVisitor);

        long itemsRemoved = purgeTaskVisitor.getEffortItemsRemoved();
        long effortItemsProcessed = purgeTaskVisitor.getEffortItemsProcessed();

        System.out.println("effortItemsProcessed = " + effortItemsProcessed);
        System.out.println("itemsRemoved = " + itemsRemoved);

        fileMarshaller.marshall(parameters.getFile(), tasks);

    }

    private static class PurgeTaskVisitor extends AbstractTaskVisitor {

        private long _effortItemsRemoved = 0;
        private long _effortItemsProcessed = 0;

        private final Date _cutoffDate;
        private final List _effortToBeDeleted = new ArrayList(50);

        private PurgeTaskVisitor(Date cutoffDate) {
            _cutoffDate = cutoffDate;
        }


        @Override
        public void processingEffort(TaskType task) {
            _effortToBeDeleted.clear();
        }

        @Override
        public void processedEffort(TaskType task) {
            List<EffortType> effortList = task.getEffort();
            for (Iterator iterator = _effortToBeDeleted.iterator(); iterator.hasNext(); ) {
                EffortType next = (EffortType) iterator.next();
                effortList.remove(next);
                _effortItemsRemoved++;
            }
        }

        private long getEffortItemsRemoved() {
            return _effortItemsRemoved;
        }


        private long getEffortItemsProcessed() {
            return _effortItemsProcessed;
        }

        protected boolean isMatch(EffortType effort) {
            // we return false he because we don't care about accumulating effort values
            return false;
        }

        @Override
        public void processEffort(EffortType effort) {

            _effortItemsProcessed++;

            final String effortDateValue = effort.getStart();

            try {
                Date effortDate = TIMESTAMP_FORMATTER.parse(effortDateValue);

                if (effortDate.before(_cutoffDate)) {
                    _effortToBeDeleted.add(effort);
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

    }

}
