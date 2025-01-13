package com.urassh.dvdrental.usecase.goods;

import com.urassh.dvdrental.domain.Goods;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.urassh.dvdrental.domain.Rental;
import com.urassh.dvdrental.domain.interfaces.GoodsRepository;
import com.urassh.dvdrental.domain.interfaces.RentalRepository;
import com.urassh.dvdrental.infrastructure.GoodsDummyRepository;
import com.urassh.dvdrental.infrastructure.RentalDummyRepository;

public class GetUnRentedGoodsUseCase {
    private final GoodsRepository goodsRepository = new GoodsDummyRepository();
    private final RentalRepository rentalRepository = new RentalDummyRepository();

    public CompletableFuture<List<Goods>> execute() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // 商品情報リポジトリから商品情報をすべて取得してリストに格納
            // その後、貸出情報リポジトリに商品IDが登録されている商品をリストからのぞく
            List<Goods> unRentedGoodsList = goodsRepository.getAll().join();

            unRentedGoodsList.removeIf(goods -> rentalRepository.getByGoodsId(goods.getId()).join() != null);

            return unRentedGoodsList;
        });
    }
}
