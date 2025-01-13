package com.urassh.dvdrental.usecase.goods;

import com.urassh.dvdrental.domain.Goods;
import com.urassh.dvdrental.domain.Rental;
import com.urassh.dvdrental.domain.interfaces.GoodsRepository;
import com.urassh.dvdrental.domain.interfaces.RentalRepository;
import com.urassh.dvdrental.infrastructure.GoodsDummyRepository;
import com.urassh.dvdrental.infrastructure.RentalDummyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class GetRentedGoodsUseCase {
    private final GoodsRepository goodsRepository = new GoodsDummyRepository();
    private final RentalRepository rentalRepository = new RentalDummyRepository();

    public CompletableFuture<List<Goods>> execute() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // 貸出情報の商品IDから貸出中の商品を取得し、戻り値用リストに格納する
            List<Rental> rentalList = rentalRepository.getAll().join();
            List<Goods> rentedGoodsList = new ArrayList<>();

            for (Rental rental : rentalList) {
                final Goods goods = goodsRepository.getById(rental.getGoodsId()).join();
                rentedGoodsList.add(goods);
            }

            return rentedGoodsList;
        });
    }
}
