package com.urassh.dvdrental.usecase.rental;

import com.google.inject.Inject;
import com.urassh.dvdrental.domain.Member;
import com.urassh.dvdrental.domain.Rental;
import com.urassh.dvdrental.domain.interfaces.RentalRepository;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class GetRentalsByMemberUseCase {
    private final RentalRepository rentalRepository;

    @Inject
    public GetRentalsByMemberUseCase(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public CompletableFuture<List<Rental>> execute(Member member) {
        return rentalRepository.getByMember(member);
    }
}
