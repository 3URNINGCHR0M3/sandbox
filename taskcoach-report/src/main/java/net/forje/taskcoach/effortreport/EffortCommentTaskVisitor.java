package net.forje.taskcoach.effortreport;

import net.forje.taskcoach.jaxb.EffortType;
import net.forje.taskcoach.types.Strings;

public class EffortCommentTaskVisitor extends AbstractTaskVisitor {

    private final String _match;

    public EffortCommentTaskVisitor(final String match) {
        _match = match;
    }

    @Override
    protected boolean isMatch(final EffortType effort) {

        boolean b = false;

        final String description = effort.getDescription();

        if (!Strings.isEmpty(description)) {
            b = description.contains(_match);
        }

        return b;
        
    }
}
