package com.urassh.dvdrental.usecase.rental;

import com.google.inject.Inject;
import com.urassh.dvdrental.domain.Rental;
import com.urassh.dvdrental.domain.interfaces.RentalRepository;
import com.urassh.dvdrental.infrastructure.RentalDummyRepository;

public class RemoveRentalUseCase {
    private final RentalRepository rentalRepository;

    @Inject
    public RemoveRentalUseCase(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public void execute(Rental rental) {
        rentalRepository.delete(rental);
    }
}
