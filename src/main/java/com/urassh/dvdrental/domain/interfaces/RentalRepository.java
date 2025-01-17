package com.urassh.dvdrental.domain.interfaces;

import com.urassh.dvdrental.domain.Rental;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface RentalRepository {
    CompletableFuture<List<Rental>> getAll();
    CompletableFuture<Rental> getById(String id);
    CompletableFuture<Rental> getByGoodsId(String goodsId);
    CompletableFuture<Rental> getByMemberId(String memberId);
    void add(Rental rental);
    void update(Rental rental);
    void delete(Rental rental);
}
