package com.urassh.dvdrental.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DateExtensionTest {

    @Test
    public void testAddDay() {
        DateExtension dateExtension = new DateExtension(new Date(0)); // Epoch time
        Date newDate = dateExtension.addDay(1);
        assertEquals(new Date(86400000L), newDate); // 1 day in milliseconds
    }

    @Test
    public void testDiffDays() {
        Date date1 = new Date(0); // Epoch time
        Date date2 = new Date(86400000L); // 1 day after epoch
        DateExtension dateExtension = new DateExtension(date1);
        int diff = dateExtension.diffDays(date2);
        assertEquals(-1, diff);
    }

    @Test
    public void testToLocalDate() {
        Date date = new Date(86400000L); // 1 day after epoch
        DateExtension dateExtension = new DateExtension(date);
        LocalDate localDate = dateExtension.toLocalDate();
        assertEquals(LocalDate.of(1970, 1, 2), localDate);
    }

    @Test
    public void testFromLocalDate_Null() {
        DateExtension dateExtension = new DateExtension();
        Date date = dateExtension.fromLocalDate(null);
        assertNull(date);
    }

    @Test
    public void testToLocalDate_Null() {
        DateExtension dateExtension = new DateExtension(null);
        LocalDate localDate = dateExtension.toLocalDate();
        assertNull(localDate);
    }
}