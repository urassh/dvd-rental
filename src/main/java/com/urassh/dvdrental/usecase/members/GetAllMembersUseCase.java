package com.urassh.dvdrental.usecase.members;

import com.urassh.dvdrental.domain.Member;
import com.urassh.dvdrental.domain.interfaces.MemberRepository;
import com.urassh.dvdrental.infrastructure.MemberDummyRepository;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class GetAllMembersUseCase {
    private final MemberRepository memberRepository = new MemberDummyRepository();

    public CompletableFuture<List<Member>> execute() {
        return memberRepository.getAll();
    }
}
