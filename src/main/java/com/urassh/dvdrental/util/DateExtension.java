package com.urassh.dvdrental.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class DateExtension {
    private final Date date;

    public DateExtension() {
        this.date = new Date();
    }

    public DateExtension(Date date) {
        this.date = date;
    }

    public Date addDay(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }

    public int diffDays(Date date) {
        long diffTime = this.date.getTime() - date.getTime();
        return (int) (diffTime / (1000 * 60 * 60 * 24));
    }

    public Date fromLocalDate(LocalDate localDate) {
        if (localDate == null) return null;
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public LocalDate toLocalDate() {
        if (this.date == null) return null;
        return this.date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
