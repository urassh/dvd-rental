package com.urassh.dvdrental.usecase.goods;

import com.urassh.dvdrental.domain.Goods;
import com.urassh.dvdrental.domain.interfaces.GoodsRepository;
import com.urassh.dvdrental.infrastructure.GoodsDummyRepository;

public class DeleteGoodsUseCase {
    private final GoodsRepository goodsRepository = new GoodsDummyRepository();

    public void execute(Goods goods) {
        goodsRepository.delete(goods);
    }
}
