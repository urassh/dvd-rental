package com.urassh.dvdrental.usecase.rental;

import com.urassh.dvdrental.domain.Goods;
import com.urassh.dvdrental.infrastructure.LocalStore;

import java.util.List;

public class GetRentalCartUseCase {
    private final LocalStore localStore = LocalStore.shared;

    public List<Goods> execute() {
        return localStore.getRentalCart();
    }
}
