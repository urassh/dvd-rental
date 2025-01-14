package com.urassh.dvdrental.usecase.goods;

import com.urassh.dvdrental.domain.Goods;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import com.urassh.dvdrental.domain.Rental;
import com.urassh.dvdrental.domain.interfaces.GoodsRepository;
import com.urassh.dvdrental.domain.interfaces.RentalRepository;
import com.urassh.dvdrental.infrastructure.GoodsDummyRepository;
import com.urassh.dvdrental.infrastructure.RentalDummyRepository;

public class GetUnRentingGoodsUseCase {
    private final GoodsRepository goodsRepository = new GoodsDummyRepository();
    private final RentalRepository rentalRepository = new RentalDummyRepository();

    public CompletableFuture<List<Goods>> execute() {
        return CompletableFuture.allOf(
                rentalRepository.getAll(),
                goodsRepository.getAll()
        ).thenCompose(voidResult -> {
            CompletableFuture<List<Rental>> rentalFuture = rentalRepository.getAll();
            CompletableFuture<List<Goods>> goodsFuture = goodsRepository.getAll();

            return rentalFuture.thenCombine(goodsFuture, (rentals, goodsList) -> {
                // 貸出中のGoods IDリストを取得
                Set<String> rentedGoodsIds = rentals.stream()
                        .map(Rental::getGoodsId)
                        .collect(Collectors.toSet());

                // 貸出中ではないGoodsをフィルタリング
                return goodsList.stream()
                        .filter(goods -> !rentedGoodsIds.contains(goods.getId()))
                        .collect(Collectors.toList());
            });
        });
    }
}
