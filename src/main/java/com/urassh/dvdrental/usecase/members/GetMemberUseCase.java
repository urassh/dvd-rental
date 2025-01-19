package com.urassh.dvdrental.usecase.members;

import com.google.inject.Inject;
import com.urassh.dvdrental.domain.Member;
import com.urassh.dvdrental.domain.interfaces.MemberRepository;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class GetMemberUseCase {
    private final MemberRepository memberRepository;

    @Inject
    public GetMemberUseCase(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public CompletableFuture<Member> execute(UUID memberId) {
        return memberRepository.getById(memberId);
    }
}
