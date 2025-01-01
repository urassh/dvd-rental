package com.urassh.dvdrental.domain;

import java.util.Calendar;
import java.util.Date;

public class Goods {
    private final String id;
    private final String title;
    private final Date releaseDate;
    private final String genre;
    private final String belongToStore;
    private final int loanCount;
    private final boolean isDisplayed;

    private static final int NEW_FEE = 300;
    private static final int OLD_FEE = 100;

    public Goods(String id, String title, Date releaseDate, String genre, String belongToStore, int loanCount, boolean isDisplayed) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.belongToStore = belongToStore;
        this.loanCount = loanCount;
        this.isDisplayed = isDisplayed;
    }

    public int getFeeWithTax() {
        final int feeWithoutTax = isNew() ? NEW_FEE : OLD_FEE;
        return (int) (feeWithoutTax * 1.1);
    }

    public boolean isNew() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -6);
        Date sixMonthsAgo = calendar.getTime();
        return releaseDate.after(sixMonthsAgo);
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public String getBelongToStore() {
        return belongToStore;
    }

    public int getLoanCount() {
        return loanCount;
    }

    public boolean isDisplayed() {
        return isDisplayed;
    }
}
