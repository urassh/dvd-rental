package com.urassh.dvdrental.usecase.returns;

import com.google.inject.Inject;
import com.urassh.dvdrental.domain.Rental;
import com.urassh.dvdrental.domain.interfaces.RentalRepository;

public class ReturnUseCase {
    private final RentalRepository rentalRepository;

    @Inject
    public ReturnUseCase(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public void execute(Rental rental) {
        rentalRepository.delete(rental);
    }
}
