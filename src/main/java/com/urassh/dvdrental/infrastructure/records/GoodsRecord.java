package com.urassh.dvdrental.infrastructure.records;

import com.google.gson.annotations.SerializedName;
import com.urassh.dvdrental.domain.Goods;

import java.util.Date;
import java.util.UUID;

public record GoodsRecord(
        @SerializedName("id") String id,
        @SerializedName("title") String title,
        @SerializedName("release_date") Date releaseDate,
        @SerializedName("genre") String genre,
        @SerializedName("belong_to_store") String belongToStore,
        @SerializedName("loan_count") int loanCount,
        @SerializedName("is_displayed") boolean isDisplayed
) {
    public static GoodsRecord from(Goods goods) {
        return new GoodsRecord(
                goods.getId().toString(),
                goods.getTitle(),
                goods.getReleaseDate(),
                goods.getGenre(),
                goods.getBelongToStore(),
                goods.getLoanCount(),
                goods.isDisplayed()
        );
    }

    public Goods toDomain() {
        return new Goods(
                UUID.fromString(id),
                title,
                releaseDate,
                genre,
                belongToStore,
                loanCount,
                isDisplayed
        );
    }
}