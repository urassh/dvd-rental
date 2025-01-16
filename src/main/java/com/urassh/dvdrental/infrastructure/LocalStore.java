package com.urassh.dvdrental.infrastructure;

import com.urassh.dvdrental.domain.Goods;
import com.urassh.dvdrental.infrastructure.serializer.GoodsSerializer;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;
import org.mapdb.Serializer;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class LocalStore {
    private static final String Identifier = "app-local-store";
    private static final String retalCountStore = "rental-count-store";
    private static final String rentalCartStore = "rental-cart-store";

    public LocalStore() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::deleteExistingStoreFile));
    }

    public void setRentalCount(int count) {
        final DB db = DBMaker.fileDB(Identifier).make();
        final HTreeMap<String, Integer> map = db
                .hashMap(retalCountStore)
                .keySerializer(Serializer.STRING)
                .valueSerializer(Serializer.INTEGER)
                .createOrOpen();

        map.put("count", count);
        db.close();
    }

    public int getRentalCount() {
        final DB db = DBMaker.fileDB(Identifier).make();
        final HTreeMap<String, Integer> map = db
                .hashMap(retalCountStore)
                .keySerializer(Serializer.STRING)
                .valueSerializer(Serializer.INTEGER)
                .createOrOpen();

        Integer count = map.get("count");
        db.close();
        return count != null ? count : 0;
    }

    public List<Goods> getRentalCart() {
        final DB db = DBMaker.fileDB(Identifier).make();
        final HTreeMap<String, Goods> map = db
                .hashMap(rentalCartStore)
                .keySerializer(Serializer.STRING)
                .valueSerializer(GoodsSerializer.GOODS)
                .createOrOpen();

        List<Goods> goodsList = List.copyOf(map.values());
        db.close();
        return goodsList;
    }

    public void addToRentalCart(Goods goods) {
        final DB db = DBMaker.fileDB(Identifier).make();
        final HTreeMap<String, Goods> map = db
                .hashMap(rentalCartStore)
                .keySerializer(Serializer.STRING)
                .valueSerializer(GoodsSerializer.GOODS)
                .createOrOpen();

        map.put(goods.getId(), goods);
        db.close();
    }

    public void removeFromRentalCart(Goods goods) {
        final DB db = DBMaker.fileDB(Identifier).make();
        final HTreeMap<String, Goods> map = db
                .hashMap(rentalCartStore)
                .keySerializer(Serializer.STRING)
                .valueSerializer(new GoodsSerializer())
                .createOrOpen();

        map.remove(goods.getId());
        setRentalCount(getRentalCount() - 1);
        db.close();
    }

    public void clearRentalCart() {
        final DB db = DBMaker.fileDB(Identifier).make();
        final HTreeMap<String, Goods> map = db
                .hashMap(rentalCartStore)
                .keySerializer(Serializer.STRING)
                .valueSerializer(new GoodsSerializer())
                .createOrOpen();

        setRentalCount(0);
        map.clear();
        db.close();
    }

    private void deleteExistingStoreFile() {
        try {
            Files.deleteIfExists(Paths.get(Identifier));
            System.out.println("既存のローカルストアファイルを削除しました: " + Identifier);
        } catch (Exception e) {
            System.err.println("ローカルストアファイルの削除に失敗しました: " + e.getMessage());
        }
    }
}
