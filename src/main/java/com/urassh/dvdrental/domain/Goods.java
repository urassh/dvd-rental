package com.urassh.dvdrental.domain;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Goods {
    private final UUID id;
    private final String title;
    private final Date releaseDate;
    private final String genre;
    private final String belongToStore;
    private final int loanCount;
    private final boolean isDisplayed;

    private static final Money NEW_FEE = new Money(300);
    private static final Money OLD_FEE = new Money(100);

    public Goods(UUID id, String title, Date releaseDate, String genre, String belongToStore, int loanCount, boolean isDisplayed) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.belongToStore = belongToStore;
        this.loanCount = loanCount;
        this.isDisplayed = isDisplayed;
    }

    public Goods(String title, Date releaseDate, String genre, String belongToStore, int loanCount, boolean isDisplayed) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.belongToStore = belongToStore;
        this.loanCount = loanCount;
        this.isDisplayed = isDisplayed;
    }

    public Money getFee() {
        return isNew() ? NEW_FEE : OLD_FEE;
    }

    public boolean isNew() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -6);
        Date sixMonthsAgo = calendar.getTime();
        return releaseDate.after(sixMonthsAgo);
    }

    public UUID getId() {
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Goods goods = (Goods) obj;
        return Objects.equals(id, goods.id);
    }
}
