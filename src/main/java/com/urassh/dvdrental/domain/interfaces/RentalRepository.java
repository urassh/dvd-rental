package com.urassh.dvdrental.domain.interfaces;

import com.urassh.dvdrental.domain.Member;
import com.urassh.dvdrental.domain.Rental;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface RentalRepository {
    CompletableFuture<List<Rental>> getAll();
    CompletableFuture<List<Rental>> getByMember(Member member);
    void add(Rental rental);
    void delete(Rental rental);
}
