package com.urassh.dvdrental.usecase.members;

import com.google.inject.Inject;
import com.urassh.dvdrental.domain.Member;
import com.urassh.dvdrental.domain.interfaces.MemberRepository;

public class AddMemberUseCase {
    private final MemberRepository memberRepository;

    @Inject
    public AddMemberUseCase(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void execute(Member member) {
        memberRepository.add(member);
    }
}
