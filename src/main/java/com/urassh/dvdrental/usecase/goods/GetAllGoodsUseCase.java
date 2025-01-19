package com.urassh.dvdrental.usecase.goods;

import com.google.inject.Inject;
import com.urassh.dvdrental.domain.Goods;
import com.urassh.dvdrental.domain.interfaces.GoodsRepository;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class GetAllGoodsUseCase {
    private final GoodsRepository goodsRepository;

    @Inject
    public GetAllGoodsUseCase(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }

    public CompletableFuture<List<Goods>> execute() {
        return goodsRepository.getAll();
    }
}
