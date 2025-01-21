package com.urassh.dvdrental.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {

    @Test
    void testConstructor_NegativeValue() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Money(-1);
        });
        assertEquals("value must be positive", exception.getMessage());
    }

    @Test
    void testAdd() {
        Money money1 = new Money(100);
        Money money2 = new Money(200);
        Money result = money1.add(money2);
        assertEquals(300, result.getValue());
    }

    @Test
    void testMultiply() {
        Money money = new Money(100);
        Money result = money.multiply(3);
        assertEquals(300, result.getValue());
    }

    @Test
    void testWithTax() {
        Money money = new Money(100);
        Money result = money.withTax();
        assertEquals(110, result.getValue());
    }

    @Test
    void testEquals_SameValue() {
        Money money1 = new Money(100);
        Money money2 = new Money(100);
        assertEquals(money1, money2);
    }

    @Test
    void testEquals_DifferentValue() {
        Money money1 = new Money(100);
        Money money2 = new Money(200);
        assertNotEquals(money1, money2);
    }

    @Test
    void testZeroConstant() {
        assertEquals(0, Money.ZERO.getValue());
    }
}