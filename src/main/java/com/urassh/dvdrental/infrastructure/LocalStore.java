package com.urassh.dvdrental.infrastructure;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;
import org.mapdb.Serializer;

import java.nio.file.Files;
import java.nio.file.Paths;

public class LocalStore {
    private static final String Identifier = "app-local-store";
    private static final String retalCountStore = "rental-count-store";

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

    private void deleteExistingStoreFile() {
        try {
            Files.deleteIfExists(Paths.get(Identifier));
            System.out.println("既存のローカルストアファイルを削除しました: " + Identifier);
        } catch (Exception e) {
            System.err.println("ローカルストアファイルの削除に失敗しました: " + e.getMessage());
        }
    }
}
