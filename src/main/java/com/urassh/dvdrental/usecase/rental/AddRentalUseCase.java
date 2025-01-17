package com.urassh.dvdrental.usecase.rental;

import com.urassh.dvdrental.domain.Goods;
import com.urassh.dvdrental.domain.Member;
import com.urassh.dvdrental.domain.Rental;
import com.urassh.dvdrental.domain.interfaces.RentalRepository;
import com.urassh.dvdrental.infrastructure.RentalDummyRepository;

import java.util.Date;

public class AddRentalUseCase {
    private final RentalRepository rentalRepository = new RentalDummyRepository();

    public void execute(Goods goods, Member member) {
        final Rental rental = new Rental(goods.getId(), member.getId(), new Date());
        rentalRepository.add(rental);
    }
}
