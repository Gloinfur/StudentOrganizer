package com.jansmoneymachine.timetablestudents.Deadlines;

public class DeadlineItem {
    private String deadlineDescription;
    private String deadlineDateFinal;
    private String deadlineTimeFinal;
    private String deadlineReminder;

    public DeadlineItem(String deadlineDescription, String deadlineDateFinal, String deadlineTimeFinal, String deadlineReminder) {
        this.deadlineDescription = deadlineDescription;
        this.deadlineDateFinal = deadlineDateFinal;
        this.deadlineTimeFinal = deadlineTimeFinal;
        this.deadlineReminder = deadlineReminder;
    }

    public String getDeadlineDescription() { return deadlineDescription; }
    public String getDeadlineDateFinal() {
        return deadlineDateFinal;
    }
    public String getDeadlineTimeFinal() {
        return deadlineTimeFinal;
    }
    public String getDeadlineReminder() { return deadlineReminder; }
}
