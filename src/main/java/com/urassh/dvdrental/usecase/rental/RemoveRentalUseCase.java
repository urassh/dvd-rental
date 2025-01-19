package com.urassh.dvdrental.usecase.rental;

import com.urassh.dvdrental.domain.Rental;
import com.urassh.dvdrental.domain.interfaces.RentalRepository;
import com.urassh.dvdrental.infrastructure.RentalDummyRepository;

public class RemoveRentalUseCase {
    private final RentalRepository rentalRepository = new RentalDummyRepository();

    public void execute(Rental rental) {
        rentalRepository.delete(rental);
    }
}
