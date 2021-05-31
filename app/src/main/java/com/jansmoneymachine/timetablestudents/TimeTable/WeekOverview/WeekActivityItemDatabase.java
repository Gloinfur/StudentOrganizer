package com.jansmoneymachine.timetablestudents.TimeTable.WeekOverview;

public class WeekActivityItemDatabase {

    private final static WeekActivityItem[] weekActivityItems = {
            new WeekActivityItem("MONDAY"),
            new WeekActivityItem("TUESDAY"),
            new WeekActivityItem("WEDNESDAY"),
            new WeekActivityItem("THURSDAY"),
            new WeekActivityItem("FRIDAY"),
            new WeekActivityItem("SATURDAY")
    };

    public static WeekActivityItem[] getWeekActivityItems() {
        return weekActivityItems;
    }
}
