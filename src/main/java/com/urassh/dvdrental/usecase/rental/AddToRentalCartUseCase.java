package com.urassh.dvdrental.usecase.rental;

import com.urassh.dvdrental.domain.Goods;
import com.urassh.dvdrental.infrastructure.LocalStore;

public class AddToRentalCartUseCase {
    private final LocalStore localStore = LocalStore.shared;

    public void execute(Goods goods) {
        localStore.addToRentalCart(goods);
        localStore.setRentalCount(localStore.getRentalCount() + 1);
    }
}
