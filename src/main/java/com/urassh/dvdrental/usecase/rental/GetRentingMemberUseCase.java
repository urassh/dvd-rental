package com.urassh.dvdrental.usecase.rental;

import com.urassh.dvdrental.domain.Goods;
import com.urassh.dvdrental.domain.Member;
import com.urassh.dvdrental.domain.Rental;
import com.urassh.dvdrental.domain.interfaces.MemberRepository;
import com.urassh.dvdrental.domain.interfaces.RentalRepository;
import com.urassh.dvdrental.infrastructure.MemberDummyRepository;
import com.urassh.dvdrental.infrastructure.RentalDummyRepository;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class GetRentingMemberUseCase {
    private final MemberRepository memberRepository = new MemberDummyRepository();
    private final RentalRepository rentalRepository = new RentalDummyRepository();

    public CompletableFuture<List<Member>> execute() {
        return CompletableFuture.allOf(
                rentalRepository.getAll(),
                memberRepository.getAll()
        ).thenCompose(voidResult -> {
            CompletableFuture<List<Rental>> rentalFuture = rentalRepository.getAll();
            CompletableFuture<List<Member>> goodsFuture = memberRepository.getAll();

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
