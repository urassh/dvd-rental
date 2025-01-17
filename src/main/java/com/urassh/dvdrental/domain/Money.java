package com.urassh.dvdrental.domain;

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

    public Money subtract(Money money) {
        final int result = value - money.value;
        return new Money(Math.max(result, 0));
    }

    public int getValue() {
        return value;
    }

    public Money withTax() {
        return new Money((int) (value * TAX_RATE));
    }
}
