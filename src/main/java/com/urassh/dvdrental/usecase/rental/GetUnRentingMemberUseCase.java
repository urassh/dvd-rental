package com.urassh.dvdrental.usecase.rental;

import com.urassh.dvdrental.domain.Member;
import com.urassh.dvdrental.domain.Rental;
import com.urassh.dvdrental.domain.interfaces.MemberRepository;
import com.urassh.dvdrental.domain.interfaces.RentalRepository;
import com.urassh.dvdrental.infrastructure.MemberDummyRepository;
import com.urassh.dvdrental.infrastructure.RentalDummyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class GetUnRentingMemberUseCase {
    private final MemberRepository memberRepository = new MemberDummyRepository();
    private final RentalRepository rentalRepository = new RentalDummyRepository();

    public CompletableFuture<List<Member>> execute() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // 商品情報リポジトリから商品情報をすべて取得してリストに格納
            // その後、貸出情報リポジトリに商品IDが登録されている商品をリストからのぞく
            List<Rental> rentalList = rentalRepository.getAll().join();
            List<Member> memberList = memberRepository.getAll().join();
            List<Member> unRentingMemberList = new ArrayList<>();

            for (Member member : memberList) {
                for (Rental rental : rentalList) {
                    if (!member.getId().equals(rental.getGoodsId())) {
                        unRentingMemberList.add(member);
                    }
                }
            }

            return unRentingMemberList;
        });
    }
}
