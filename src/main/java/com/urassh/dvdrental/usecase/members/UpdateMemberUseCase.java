package com.urassh.dvdrental.usecase.members;

import com.urassh.dvdrental.domain.Member;
import com.urassh.dvdrental.domain.interfaces.MemberRepository;
import com.urassh.dvdrental.infrastructure.MemberDummyRepository;

public class UpdateMemberUseCase {
    private final MemberRepository memberRepository = new MemberDummyRepository();

    public void execute(Member member) {
        memberRepository.update(member);
    }
}
