package com.urassh.dvdrental.usecase.rental;

import com.google.inject.Inject;
import com.urassh.dvdrental.domain.Goods;
import com.urassh.dvdrental.domain.Member;
import com.urassh.dvdrental.domain.Rental;
import com.urassh.dvdrental.domain.interfaces.RentalRepository;
import java.util.Date;

public class AddRentalUseCase {
    private final RentalRepository rentalRepository;

    @Inject
    public AddRentalUseCase(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public void execute(Goods goods, Member member) {
        final Rental rental = new Rental(goods, member, new Date());
        rentalRepository.add(rental);
    }
}
