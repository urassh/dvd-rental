package com.urassh.dvdrental.usecase.goods;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import com.google.inject.Inject;
import com.urassh.dvdrental.domain.Goods;
import com.urassh.dvdrental.domain.Rental;
import com.urassh.dvdrental.domain.interfaces.GoodsRepository;
import com.urassh.dvdrental.domain.interfaces.RentalRepository;
import com.urassh.dvdrental.repository.LocalStore;

public class GetUnRentingGoodsUseCase {
    private final GoodsRepository goodsRepository;
    private final RentalRepository rentalRepository;
    private final LocalStore localStore = LocalStore.shared;

    @Inject
    public GetUnRentingGoodsUseCase(GoodsRepository goodsRepository, RentalRepository rentalRepository) {
        this.goodsRepository = goodsRepository;
        this.rentalRepository = rentalRepository;
    }

    public CompletableFuture<List<Goods>> execute() {
        return CompletableFuture.allOf(
                rentalRepository.getAll(),
                goodsRepository.getAll()
        ).thenCompose(voidResult -> {
            CompletableFuture<List<Rental>> rentalFuture = rentalRepository.getAll();
            CompletableFuture<List<Goods>> goodsFuture = goodsRepository.getAll();

            return rentalFuture.thenCombine(goodsFuture, (rentals, goodsList) -> {
                Set<String> rentingGoodsIds = rentals.stream()
                        .map(Rental::getGoods)
                        .map(Goods::getId)
                        .map(UUID::toString)
                        .collect(Collectors.toSet());

                return goodsList.stream()
                        .filter(goods -> !rentingGoodsIds.contains(goods.getId().toString()))
                        .filter(goods -> !localStore.getRentalCart().contains(goods))
                        .collect(Collectors.toList());
            });
        });
    }
}
