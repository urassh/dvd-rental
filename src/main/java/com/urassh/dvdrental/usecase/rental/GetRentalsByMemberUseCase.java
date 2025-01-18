package com.urassh.dvdrental.usecase.rental;

import com.urassh.dvdrental.domain.Member;
import com.urassh.dvdrental.domain.Rental;
import com.urassh.dvdrental.domain.interfaces.RentalRepository;
import com.urassh.dvdrental.infrastructure.RentalDummyRepository;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class GetRentalsByMemberUseCase {
    private final RentalRepository rentalRepository = new RentalDummyRepository();

    public CompletableFuture<List<Rental>> execute(Member member) {
        return rentalRepository.getByMember(member);
    }
}
