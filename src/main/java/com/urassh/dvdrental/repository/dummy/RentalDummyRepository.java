package com.urassh.dvdrental.repository.dummy;

import com.urassh.dvdrental.domain.Member;
import com.urassh.dvdrental.domain.Rental;
import com.urassh.dvdrental.domain.interfaces.RentalRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class RentalDummyRepository implements RentalRepository {
    private static List<Rental> rentalList = new ArrayList<>(List.of());

    @Override
    public CompletableFuture<List<Rental>> getAll() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return rentalList;
        });
    }

    @Override
    public CompletableFuture<List<Rental>> getByMember(Member member) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            List<Rental> memberRentalList = new ArrayList<>();
            for (Rental rental : rentalList) {
                if (rental.getMember().getId().equals(member.getId())) {
                    memberRentalList.add(rental);
                }
            }
            return memberRentalList;
        });
    }

    @Override
    public void add(Rental rental) {
        rentalList.add(rental);
    }

    @Override
    public void delete(Rental rental) {
        rentalList.removeIf(existingRental -> existingRental.getId().equals(rental.getId()));
    }
}
