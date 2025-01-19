package com.urassh.dvdrental.usecase.goods;

import com.google.inject.Inject;
import com.urassh.dvdrental.domain.Goods;
import com.urassh.dvdrental.domain.interfaces.GoodsRepository;

public class DeleteGoodsUseCase {
    private final GoodsRepository goodsRepository;

    @Inject
    public DeleteGoodsUseCase(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }

    public void execute(Goods goods) {
        goodsRepository.delete(goods);
    }
}
