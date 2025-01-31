package com.urassh.dvdrental.repository.impl;

import com.google.inject.Inject;
import com.urassh.dvdrental.domain.Goods;
import com.urassh.dvdrental.domain.interfaces.GoodsRepository;
import com.urassh.dvdrental.infrastructure.records.GoodsRecord;
import com.urassh.dvdrental.repository.interfaces.GoodsGateway;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class GoodsRepositoryImpl implements GoodsRepository {
    private final GoodsGateway goodsGateway;

    @Inject
    public GoodsRepositoryImpl(GoodsGateway goodsGateway) {
        this.goodsGateway = goodsGateway;
    }

    @Override
    public CompletableFuture<List<Goods>> getAll() {
        return goodsGateway.getAll().thenApply(goodsRecords ->
            goodsRecords.stream()
                .map(GoodsRecord::toDomain)
                .collect(Collectors.toList())
        );
    }

    @Override
    public CompletableFuture<Void> add(Goods goods) {
        GoodsRecord goodsRecord = GoodsRecord.from(goods);
        return goodsGateway.add(goodsRecord);
    }

    @Override
    public CompletableFuture<Void> update(Goods goods) {
        GoodsRecord goodsRecord = GoodsRecord.from(goods);
        return goodsGateway.update(goodsRecord);
    }

    @Override
    public CompletableFuture<Void> delete(Goods goods) {
        GoodsRecord goodsRecord = GoodsRecord.from(goods);
        return goodsGateway.delete(goodsRecord);
    }
}
