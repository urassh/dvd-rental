package com.urassh.dvdrental.usecase.rental;

import com.google.inject.Inject;
import com.urassh.dvdrental.domain.Rental;
import com.urassh.dvdrental.domain.interfaces.RentalRepository;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class GetAllRentalsUseCase {
    private final RentalRepository rentalRepository;

    @Inject
    public GetAllRentalsUseCase(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public CompletableFuture<List<Rental>> execute() {
        return rentalRepository.getAll();
    }
}
