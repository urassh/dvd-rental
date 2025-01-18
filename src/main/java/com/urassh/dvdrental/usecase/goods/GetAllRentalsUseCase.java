package com.urassh.dvdrental.usecase.goods;

import com.urassh.dvdrental.domain.Rental;
import com.urassh.dvdrental.domain.interfaces.RentalRepository;
import com.urassh.dvdrental.infrastructure.RentalDummyRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class GetAllRentalsUseCase {
    private final RentalRepository rentalRepository = new RentalDummyRepository();

    public CompletableFuture<List<Rental>> execute() {
        return rentalRepository.getAll();
    }
}
