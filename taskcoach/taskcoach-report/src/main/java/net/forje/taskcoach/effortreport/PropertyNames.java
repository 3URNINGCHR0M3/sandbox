package net.forje.taskcoach.effortreport;

public enum PropertyNames {

    JiraKey("erx.JiraKey"), Release("erx.release"), Certification("erx.certification"), Vendor("erx.vendor"),
    Customer("erx.customer"), Client("client"), Project("project"), Phase("phase"), Activity("activity"),
    Task("task"), Details("details"), Component("erx.component"), EffortDescription("erx.effortDescription");

    private PropertyNames(final String string) {
        _string = string;
    }

    private final String _string;

    @Override
    public String toString() {
        return _string;
    }

}
