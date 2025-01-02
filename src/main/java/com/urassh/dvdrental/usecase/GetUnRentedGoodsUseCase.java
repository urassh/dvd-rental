package com.urassh.dvdrental.usecase;

import com.urassh.dvdrental.domain.Goods;

import java.util.concurrent.CompletableFuture;

public class GetUnRentedGoodsUseCase {
    public CompletableFuture<Goods[]> execute() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            return new Goods[] {
                new Goods("1", "title1", null, "genre1", "store1", 0, true),
                new Goods("2", "title2", null, "genre2", "store2", 0, true),
                new Goods("3", "title3", null, "genre3", "store3", 0, true),
                new Goods("4", "title4", null, "genre4", "store4", 0, true),
                new Goods("5", "title5", null, "genre5", "store5", 0, true),
            };
        });
    }
}
