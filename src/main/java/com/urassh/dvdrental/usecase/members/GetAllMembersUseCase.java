package com.urassh.dvdrental.usecase.members;

import com.google.inject.Inject;
import com.urassh.dvdrental.domain.Member;
import com.urassh.dvdrental.domain.interfaces.MemberRepository;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class GetAllMembersUseCase {
    private final MemberRepository memberRepository;

    @Inject
    public GetAllMembersUseCase(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public CompletableFuture<List<Member>> execute() {
        return memberRepository.getAll();
    }
}
