package com.urassh.dvdrental.usecase.rental;

import com.urassh.dvdrental.domain.Member;
import com.urassh.dvdrental.domain.interfaces.MemberRepository;
import com.urassh.dvdrental.infrastructure.MemberDummyRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class GetAllMemberUseCase {
    private final MemberRepository memberRepository = new MemberDummyRepository();

    public CompletableFuture<List<Member>> execute() {
        return memberRepository.getAll();
    }
}
