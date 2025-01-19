package com.urassh.dvdrental.usecase.goods;

import com.google.inject.Inject;
import com.urassh.dvdrental.domain.Goods;
import com.urassh.dvdrental.domain.interfaces.GoodsRepository;

public class AddGoodsUseCase {
    private final GoodsRepository goodsRepository;

    @Inject
    public AddGoodsUseCase(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }

    public void execute(Goods goods) {
        goodsRepository.add(goods);
    }
}