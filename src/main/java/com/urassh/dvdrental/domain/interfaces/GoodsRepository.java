package com.urassh.dvdrental.domain.interfaces;

import com.urassh.dvdrental.domain.Goods;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface GoodsRepository {
    CompletableFuture<List<Goods>> getAll();
    CompletableFuture<Goods> getById(String id);
    CompletableFuture<Void> add(Goods goods);
    CompletableFuture<Void> update(Goods goods);
    CompletableFuture<Void> delete(String id);
}
