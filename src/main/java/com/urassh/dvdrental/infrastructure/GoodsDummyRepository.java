package com.urassh.dvdrental.infrastructure;

import com.urassh.dvdrental.domain.Goods;
import com.urassh.dvdrental.domain.interfaces.GoodsRepository;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class GoodsDummyRepository implements GoodsRepository {
    private static List<Goods> goodsList = List.of(
            new Goods("1", "The Matrix", new Date(1672531200000L), "Sci-Fi", "MovieStore1", 0, true),  // 2023-01-01
            new Goods("2", "Inception", new Date(1675036800000L), "Sci-Fi", "MovieStore2", 0, true),  // 2023-01-30
            new Goods("3", "Spirited Away", new Date(1677628800000L), "Animation", "MovieStore3", 0, true),  // 2023-03-01
            new Goods("4", "The Dark Knight", new Date(1680307200000L), "Action", "MovieStore4", 0, true),  // 2023-04-01
            new Goods("5", "Parasite", new Date(1682899200000L), "Thriller", "MovieStore5", 0, true),  // 2023-05-01
            new Goods("6", "Avengers: Endgame", new Date(1685577600000L), "Action", "MovieStore6", 0, true),  // 2023-06-01
            new Goods("7", "Frozen II", new Date(1688169600000L), "Animation", "MovieStore7", 0, true),  // 2023-07-01
            new Goods("8", "Joker", new Date(1690848000000L), "Drama", "MovieStore8", 0, true),  // 2023-08-01
            new Goods("9", "Interstellar", new Date(1693526400000L), "Sci-Fi", "MovieStore9", 0, true),  // 2023-09-01
            new Goods("10", "The Godfather", new Date(1696118400000L), "Crime", "MovieStore10", 0, true), // 2023-10-01
            new Goods("11", "Forrest Gump", new Date(1698796800000L), "Drama", "MovieStore11", 0, true), // 2023-11-01
            new Goods("12", "The Lion King", new Date(1701388800000L), "Animation", "MovieStore12", 0, true), // 2023-12-01
            new Goods("13", "Shawshank Redemption", new Date(1704067200000L), "Drama", "MovieStore13", 0, true), // 2023-12-31
            new Goods("14", "Your Name", new Date(1706659200000L), "Romance", "MovieStore14", 0, true), // 2024-01-30
            new Goods("15", "Harry Potter", new Date(1709251200000L), "Fantasy", "MovieStore15", 0, true), // 2024-03-01
            new Goods("16", "Demon Slayer: Mugen Train", new Date(1711843200000L), "Animation", "MovieStore16", 0, true), // 2024-04-01
            new Goods("17", "The Avengers", new Date(1714435200000L), "Action", "MovieStore17", 0, true), // 2024-05-01
            new Goods("18", "Toy Story", new Date(1717113600000L), "Animation", "MovieStore18", 0, true), // 2024-06-01
            new Goods("19", "A Silent Voice", new Date(1719705600000L), "Drama", "MovieStore19", 0, true), // 2024-07-01
            new Goods("20", "The Pursuit of Happyness", new Date(1722384000000L), "Biography", "MovieStore20", 0, true)  // 2024-08-01
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
