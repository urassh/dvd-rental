package com.urassh.dvdrental.usecase.rental;

import com.urassh.dvdrental.domain.Member;
import com.urassh.dvdrental.domain.interfaces.MemberRepository;
import com.urassh.dvdrental.domain.interfaces.RentalRepository;
import com.urassh.dvdrental.infrastructure.MemberDummyRepository;
import com.urassh.dvdrental.infrastructure.RentalDummyRepository;

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

            // 会員情報リポジトリから会員情報をすべて取得してリストに格納
            // その後、貸出情報リポジトリに会員IDが登録されている会員をリストからのぞく
            List<Member> unRentingMemberList = memberRepository.getAll().join();

            unRentingMemberList.removeIf(member -> rentalRepository.getByMemberId(member.getId()).join() != null);

            return unRentingMemberList;
        });
    }
}
