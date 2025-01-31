package com.urassh.dvdrental.infrastructure.records;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.urassh.dvdrental.domain.Goods;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsRecord {
    @SerializedName("id")
    private String id;

    @SerializedName("title")
    private String title;

    @SerializedName("release_date")
    private Date releaseDate;

    @SerializedName("genre")
    private String genre;

    @SerializedName("belong_to_store")
    private String belongToStore;

    @SerializedName("loan_count")
    private int loanCount;

    @SerializedName("is_displayed")
    private boolean isDisplayed;

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
