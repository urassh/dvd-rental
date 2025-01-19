package com.urassh.dvdrental.usecase.goods;

import com.google.inject.Inject;
import com.urassh.dvdrental.domain.Goods;
import com.urassh.dvdrental.domain.interfaces.GoodsRepository;
import com.urassh.dvdrental.infrastructure.GoodsDummyRepository;

public class UpdateGoodsUseCase {
    private final GoodsRepository goodsRepository;

    @Inject
    public UpdateGoodsUseCase(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }

    public void execute(Goods goods) {
        goodsRepository.update(goods);
    }
}
