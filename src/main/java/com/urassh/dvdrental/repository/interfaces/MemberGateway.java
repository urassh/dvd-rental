package com.urassh.dvdrental.repository.interfaces;


import com.urassh.dvdrental.infrastructure.records.MemberRecord;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface MemberGateway {
    CompletableFuture<List<MemberRecord>> getAll();
    CompletableFuture<MemberRecord> getById(UUID id);
    CompletableFuture<Void> add(MemberRecord member);
    CompletableFuture<Void> update(MemberRecord member);
    CompletableFuture<Void> delete(MemberRecord member);
}
