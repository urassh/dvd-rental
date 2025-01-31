package com.urassh.dvdrental.repository.interfaces;

import com.urassh.dvdrental.infrastructure.records.GoodsRecord;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface GoodsGateway {
    CompletableFuture<List<GoodsRecord>> getAll();
    CompletableFuture<Void> add(GoodsRecord goods);
    CompletableFuture<Void> update(GoodsRecord goods);
    CompletableFuture<Void> delete(GoodsRecord goods);
}

