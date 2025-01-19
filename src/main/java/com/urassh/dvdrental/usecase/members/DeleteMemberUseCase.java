package com.urassh.dvdrental.usecase.members;

import com.google.inject.Inject;
import com.urassh.dvdrental.domain.Member;
import com.urassh.dvdrental.domain.interfaces.MemberRepository;

public class DeleteMemberUseCase {
    private final MemberRepository memberRepository;

    @Inject
    public DeleteMemberUseCase(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void execute(Member member) {
        memberRepository.delete(member);
    }
}
