package com.urassh.dvdrental.domain.interfaces;

import com.urassh.dvdrental.domain.Goods;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface RentalRepository {
    CompletableFuture<List<Goods>> getAll();
    CompletableFuture<Goods> getByGoodsId(String id);
    CompletableFuture<Goods> getByMemberId(String id);
    CompletableFuture<Void> add(Goods goods);
    CompletableFuture<Void> delete(String id);
}
