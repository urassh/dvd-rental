package com.urassh.dvdrental.repository.impl;

import com.google.inject.Inject;
import com.urassh.dvdrental.domain.Member;
import com.urassh.dvdrental.domain.interfaces.MemberRepository;
import com.urassh.dvdrental.infrastructure.records.MemberRecord;
import com.urassh.dvdrental.repository.interfaces.MemberGateway;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class MemberRepositoryImpl implements MemberRepository {
    private final MemberGateway memberGateway;

    @Inject
    public MemberRepositoryImpl(MemberGateway memberGateway) {
        this.memberGateway = memberGateway;
    }

    @Override
    public CompletableFuture<List<Member>> getAll() {
        return memberGateway.getAll().thenApply(memberRecords ->
            memberRecords.stream()
                .map(MemberRecord::toDomain)
                .collect(Collectors.toList())
        );
    }

    @Override
    public CompletableFuture<Member> getById(UUID id) {
        return memberGateway.getById(id).thenApply(MemberRecord::toDomain);
    }

    @Override
    public void add(Member member) {
        memberGateway.add(MemberRecord.from(member));
    }

    @Override
    public void update(Member member) {
        memberGateway.update(MemberRecord.from(member));
    }

    @Override
    public void delete(Member member) {
        memberGateway.delete(MemberRecord.from(member));
    }
}
