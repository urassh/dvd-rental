package com.urassh.dvdrental.usecase.goods;

import com.urassh.dvdrental.domain.Goods;
import com.urassh.dvdrental.domain.interfaces.GoodsRepository;
import com.urassh.dvdrental.infrastructure.GoodsDummyRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class GetAllGoodsUseCase {
    private final GoodsRepository goodsRepository = new GoodsDummyRepository();

    public CompletableFuture<List<Goods>> execute() {
        return goodsRepository.getAll();
    }
}
