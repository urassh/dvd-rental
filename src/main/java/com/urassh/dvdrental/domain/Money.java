package com.urassh.dvdrental.domain;

import java.util.Objects;

public class Money {
    private final int value;
    private static final Double TAX_RATE = 1.1;
    public static final Money ZERO = new Money(0);

    public Money(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("value must be positive");
        }

        this.value = value;
    }

    public Money add(Money money) {
        return new Money(value + money.value);
    }

    public Money multiply(int multiplier) {
        return new Money(value * multiplier);
    }

    public int getValue() {
        return value;
    }

    public Money withTax() {
        return new Money((int) (value * TAX_RATE));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Money money = (Money) obj;
        return Objects.equals(value, money.getValue());
    }
}
