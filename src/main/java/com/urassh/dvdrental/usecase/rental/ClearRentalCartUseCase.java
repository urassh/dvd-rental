package com.urassh.dvdrental.usecase.rental;

import com.urassh.dvdrental.infrastructure.LocalStore;

public class ClearRentalCartUseCase {
    private final LocalStore localStore = LocalStore.shared;

    public void execute() {
        localStore.clearRentalCart();
        localStore.setRentalCount(0);
    }
}
