package com.urassh.dvdrental.infrastructure;

import com.urassh.dvdrental.domain.Rental;
import com.urassh.dvdrental.domain.interfaces.RentalRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class RentalDummyRepository implements RentalRepository {
    private static List<Rental> rentalList = List.of(
            new Rental("1", "1", "1", new Date(1990, Calendar.JANUARY, 1)),
            new Rental("2", "2", "1", new Date(1990, Calendar.JANUARY, 1)),
            new Rental("3", "3", "2", new Date(1990, Calendar.JANUARY, 1))
    );

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

    public CompletableFuture<Rental> getById(String id) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            for (Rental rental : rentalList) {
                if (rental.getId().equals(id)) {
                    return rental;
                }
            }
            return null;
        });
    }

    public CompletableFuture<Rental> getByGoodsId(String goodsId) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            for (Rental rental : rentalList) {
                if (rental.getGoodsId().equals(goodsId)) {
                    return rental;
                }
            }
            return null;
        });
    }

    public CompletableFuture<Rental> getByMemberId(String memberId) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000); // Simulate delay
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            for (Rental rental : rentalList) {
                if (rental.getMenberId().equals(memberId)) {
                    return rental;
                }
            }
            return null;
        });
    }

    @Override
    public CompletableFuture<Void> add(Rental rental) {
        rentalList.add(rental);
        return null;
    }

    public CompletableFuture<Void> update(Rental rental) {
        for (int i = 0; i < rentalList.size(); i++) {
            if (rentalList.get(i).getId().equals(rental.getId())) {
                rentalList.set(i, rental);
                return null;
            }
        }
        return null;
    }

    public CompletableFuture<Void> delete(Rental rental) {
        rentalList.removeIf(existingRental -> existingRental.getId().equals(rental.getId()));
        return null;
    }
}
