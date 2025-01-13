package com.urassh.dvdrental.usecase.members;

import com.urassh.dvdrental.domain.Member;
import com.urassh.dvdrental.domain.interfaces.MemberRepository;
import com.urassh.dvdrental.infrastructure.MemberDummyRepository;

import java.util.concurrent.CompletableFuture;

public class GetMemberUseCase {
    private final MemberRepository memberRepository = new MemberDummyRepository();

    public CompletableFuture<Member> execute(String memberId) {
        return memberRepository.getById(memberId);
    }
}
