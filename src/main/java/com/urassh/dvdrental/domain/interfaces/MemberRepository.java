package com.urassh.dvdrental.domain.interfaces;

import com.urassh.dvdrental.domain.Member;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface MemberRepository {
    CompletableFuture<List<Member>> getAll();
    CompletableFuture<Member> getById(UUID id);
    void add(Member member);
    void update(Member member);
    void delete(Member member);
}
