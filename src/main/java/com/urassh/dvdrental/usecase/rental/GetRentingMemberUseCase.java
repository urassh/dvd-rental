package com.urassh.dvdrental.usecase.rental;

import com.urassh.dvdrental.domain.Goods;
import com.urassh.dvdrental.domain.Member;
import com.urassh.dvdrental.domain.Rental;
import com.urassh.dvdrental.domain.interfaces.MemberRepository;
import com.urassh.dvdrental.domain.interfaces.RentalRepository;
import com.urassh.dvdrental.infrastructure.MemberDummyRepository;
import com.urassh.dvdrental.infrastructure.RentalDummyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class GetRentingMemberUseCase {
    private final MemberRepository memberRepository = new MemberDummyRepository();
    private final RentalRepository rentalRepository = new RentalDummyRepository();

    public CompletableFuture<List<Member>> execute() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // 貸出情報の商品IDから貸出中の商品を取得し、戻り値用リストに格納する
            List<Rental> rentalList = rentalRepository.getAll().join();
            List<Member> rentingMemberList = new ArrayList<>();

            for (Rental rental : rentalList) {
                final Member member = memberRepository.getById(rental.getMemberId()).join();
                rentingMemberList.add(member);
            }

            return rentingMemberList;
        });
    }
}
