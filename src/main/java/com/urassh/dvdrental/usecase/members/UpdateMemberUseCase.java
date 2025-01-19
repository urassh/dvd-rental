package com.urassh.dvdrental.usecase.members;

import com.google.inject.Inject;
import com.urassh.dvdrental.domain.Member;
import com.urassh.dvdrental.domain.interfaces.MemberRepository;
import com.urassh.dvdrental.infrastructure.MemberDummyRepository;

public class UpdateMemberUseCase {
    private final MemberRepository memberRepository;

    @Inject
    public UpdateMemberUseCase(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void execute(Member member) {
        memberRepository.update(member);
    }
}
