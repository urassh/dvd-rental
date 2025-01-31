package com.urassh.dvdrental.usecase.rental.cart;

import com.urassh.dvdrental.repository.LocalStore;

public class ClearRentalCartUseCase {
    private final LocalStore localStore = LocalStore.shared;

    public void execute() {
        localStore.clearRentalCart();
        localStore.setRentalCount(0);
    }
}
