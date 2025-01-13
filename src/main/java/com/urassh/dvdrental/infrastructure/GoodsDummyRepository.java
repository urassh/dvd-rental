package com.urassh.dvdrental.infrastructure;

import com.urassh.dvdrental.domain.Goods;
import com.urassh.dvdrental.domain.interfaces.GoodsRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class GoodsDummyRepository implements GoodsRepository {
    private static List<Goods> goodsList = List.of(
            new Goods("1", "title1", null, "genre1", "store1", 0, true),
            new Goods("2", "title2", null, "genre2", "store2", 0, true),
            new Goods("3", "title3", null, "genre3", "store3", 0, true),
            new Goods("4", "title4", null, "genre4", "store4", 0, true),
            new Goods("5", "title5", null, "genre5", "store5", 0, true)
    );

    public CompletableFuture<List<Goods>> getAll() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return goodsList;
        });
    }

    public CompletableFuture<Goods> getById(String id) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            for (Goods good : goodsList) {
                if (good.getId().equals(id)) {
                    return good;
                }
            }
            return null;
        });
    }

    @Override
    public CompletableFuture<Void> add(Goods goods) {
        goodsList.add(goods);
        return null;
    }

    public CompletableFuture<Void> update(Goods good) {
        for (int i = 0; i < goodsList.size(); i++) {
            if (goodsList.get(i).getId().equals(good.getId())) {
                goodsList.set(i, good);
                return null;
            }
        }
        return null;
    }

    public CompletableFuture<Void> delete(Goods good) {
        goodsList.removeIf(existingGood -> existingGood.getId().equals(good.getId()));
        return null;
    }
}
