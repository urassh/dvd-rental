package com.urassh.dvdrental.infrastructure;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;
import org.mapdb.Serializer;

public class LocalStore {
    private static final String Identifier = "app-local-store";
    private static final String retalCountStore = "rental-count-store";

    public void setRentalCount(int count) {
        DB db = DBMaker.fileDB(Identifier).make();
        HTreeMap<String, Integer> map = db
                .hashMap(retalCountStore)
                .keySerializer(Serializer.STRING)
                .valueSerializer(Serializer.INTEGER)
                .createOrOpen();

        map.put("count", count);
        db.close();
    }

    public int getRentalCount() {
        DB db = DBMaker.fileDB(Identifier).make();
        HTreeMap<String, Integer> map = db
                .hashMap(retalCountStore)
                .keySerializer(Serializer.STRING)
                .valueSerializer(Serializer.INTEGER)
                .createOrOpen();

        Integer count = map.get("count");
        db.close();
        return count != null ? count : 0;
    }

}
