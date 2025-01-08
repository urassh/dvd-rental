package com.urassh.dvdrental.domain;

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
        // 日付加算は汎用な処理なので別ファイルにつくったほうがよい↓
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(rentalDate);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        this.dueDate = calendar.getTime();
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
