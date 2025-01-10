package com.urassh.dvdrental.util;

import java.util.Calendar;
import java.util.Date;

public class DateExtension {
    private final Date date;

    public DateExtension(Date date) {
        this.date = date;
    }

    // add number of days.
    public Date addDay(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }

    // add number of weeks.
    public Date addWeek(int week) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.WEEK_OF_MONTH, week);
        return calendar.getTime();
    }

    // add number of months.
    public Date addMonth(int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }
}
