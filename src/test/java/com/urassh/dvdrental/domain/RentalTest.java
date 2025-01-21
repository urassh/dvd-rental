package com.urassh.dvdrental.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class RentalTest {
    private Goods goods;
    private Member member;
    private Date rentalDate;
    private Rental rental;

    @BeforeEach
    void setUp() {
        goods = new Goods(UUID.randomUUID(), "Test Movie", new Date(), "Action", "Store A", 10, true);
        member = new Member(UUID.randomUUID(), "John Doe", "1234567890", "123 Main St", new Date());
        rentalDate = new Date();
        rental = new Rental(goods, member, rentalDate);
    }

    @Test
    void testConstructor_WithId() {
        String id = UUID.randomUUID().toString();
        Rental rental = new Rental(id, goods, member, rentalDate);
        assertEquals(id, rental.getId());
        assertEquals(goods, rental.getGoods());
        assertEquals(member, rental.getMember());
        assertEquals(rentalDate, rental.getRentalDate());
        assertNotNull(rental.getDueDate());
    }

    @Test
    void testConstructor_WithoutId() {
        Rental rental = new Rental(goods, member, rentalDate);
        assertNotNull(rental.getId());
        assertEquals(goods, rental.getGoods());
        assertEquals(member, rental.getMember());
        assertEquals(rentalDate, rental.getRentalDate());
        assertNotNull(rental.getDueDate());
    }

    @Test
    void testGetLateFee_NoLateFee() {
        assertEquals(Money.ZERO, rental.getLateFee());
    }

    @Test
    void testGetLateFee_WithLateFee() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -10); // 10 days ago
        rental = new Rental(goods, member, calendar.getTime());
        assertTrue(rental.isOverdue());
        assertEquals(new Money(300), rental.getLateFee()); // 3 days late * 100 per day
    }

    @Test
    void testIsOverdue_NotOverdue() {
        assertFalse(rental.isOverdue());
    }

    @Test
    void testIsOverdue_Overdue() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -10); // 10 days ago
        rental = new Rental(goods, member, calendar.getTime());
        assertTrue(rental.isOverdue());
    }
}