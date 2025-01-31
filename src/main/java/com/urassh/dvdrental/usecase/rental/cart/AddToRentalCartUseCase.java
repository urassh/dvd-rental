package com.urassh.dvdrental.usecase.rental.cart;

import com.urassh.dvdrental.domain.Goods;
import com.urassh.dvdrental.repository.LocalStore;

public class AddToRentalCartUseCase {
    private final LocalStore localStore = LocalStore.shared;

    public void execute(Goods goods) {
        localStore.addToRentalCart(goods);
        localStore.setRentalCount(localStore.getRentalCount() + 1);
    }
}
