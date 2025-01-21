package com.urassh.dvdrental.domain;

import com.urassh.dvdrental.util.DateExtension;
import java.util.Date;
import java.util.UUID;

public class Rental {
    private final String id;
    private final Goods goods;
    private final Member member;
    private final Date rentalDate;
    private final Date dueDate;

    private static final Money LATE_FEE_PER_DAY = new Money(100);
    private static final int RENTAL_PERIOD_DAYS = 7;

    public Rental(String id, Goods goods, Member member, Date rentalDate) {
        this.id = id;
        this.goods = goods;
        this.member = member;
        this.rentalDate = rentalDate;
        this.dueDate = new DateExtension(rentalDate).addDay(RENTAL_PERIOD_DAYS);
    }

    public Rental(Goods goods, Member member, Date rentalDate) {
        this.id = UUID.randomUUID().toString();
        this.goods = goods;
        this.member = member;
        this.rentalDate = rentalDate;
        this.dueDate = new DateExtension(rentalDate).addDay(RENTAL_PERIOD_DAYS);
    }

    public String getId() {
        return id;
    }

    public Goods getGoods() {
        return goods;
    }

    public Member getMember() { return member; }

    public Date getRentalDate() { return rentalDate; }

    public Date getDueDate() { return dueDate; }

    public Money getLateFee() {
        if (isOverdue()) return LATE_FEE_PER_DAY.multiply(getOverdueDays());
        return Money.ZERO;
    }

    public boolean isOverdue() {
        return new Date().after(dueDate);
    }

    private int getOverdueDays() {
        final Date today = new Date();
        final int diffDate = new DateExtension(today).diffDays(dueDate);

        return isOverdue() ? Math.max(diffDate, 0) : 0;
    }
}
