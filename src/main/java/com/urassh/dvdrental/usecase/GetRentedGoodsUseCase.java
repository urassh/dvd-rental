package com.urassh.dvdrental.usecase;

import com.urassh.dvdrental.domain.Goods;
import com.urassh.dvdrental.infrastructure.GoodsDummyRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class GetRentedGoodsUseCase {
    private final GoodsDummyRepository goodsRepository = new GoodsDummyRepository();

    public CompletableFuture<List<Goods>> execute() {
        return goodsRepository.getAll();
    }
}
