package com.urassh.dvdrental.repository.serializer;

import com.urassh.dvdrental.domain.Goods;
import org.jetbrains.annotations.NotNull;
import org.mapdb.DataInput2;
import org.mapdb.DataOutput2;
import org.mapdb.Serializer;

import java.io.IOException;
import java.util.Date;

public class GoodsSerializer implements Serializer<Goods> {
    public static final GoodsSerializer GOODS = new GoodsSerializer();

    @Override
    public void serialize(@NotNull DataOutput2 dataOutput2, @NotNull Goods goods) throws IOException {
        dataOutput2.writeUTF(goods.getId().toString());
        dataOutput2.writeUTF(goods.getTitle());
        dataOutput2.writeLong(goods.getReleaseDate().getTime());
        dataOutput2.writeUTF(goods.getGenre());
        dataOutput2.writeUTF(goods.getBelongToStore());
        dataOutput2.writeInt(goods.getLoanCount());
        dataOutput2.writeBoolean(goods.isDisplayed());
    }

    @Override
    public Goods deserialize(@NotNull DataInput2 dataInput2, int i) throws IOException {
        java.util.UUID id = java.util.UUID.fromString(dataInput2.readUTF());
        String title = dataInput2.readUTF();
        Date releaseDate = new Date(dataInput2.readLong());
        String genre = dataInput2.readUTF();
        String belongToStore = dataInput2.readUTF();
        int loanCount = dataInput2.readInt();
        boolean isDisplayed = dataInput2.readBoolean();
        return new Goods(id, title, releaseDate, genre, belongToStore, loanCount, isDisplayed);
    }
}