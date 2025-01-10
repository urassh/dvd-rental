package com.urassh.dvdrental.domain;

import com.urassh.dvdrental.util.DateExtension;

import java.util.Calendar;
import java.util.Date;

public class Rental {
    private final String id;
    private final String goodsId;
    private final String menberId;
    private final Date rentalDate;
    private final Date dueDate;

    public Rental(String id, String goodsId, String menberId, Date rentalDate) {
        this.id = id;
        this.goodsId = goodsId;
        this.menberId = menberId;
        this.rentalDate = rentalDate;
        // 返却期限を計算して代入する
        this.dueDate = new DateExtension(rentalDate).addDay(7);
    }

    public String getId() {
        return id;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public String getMenberId() { return menberId; }

    public Date getRentalDate() { return rentalDate; }

    public Date getDueDate() { return dueDate; }
}
