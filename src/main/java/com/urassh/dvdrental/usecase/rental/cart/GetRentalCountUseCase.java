package com.urassh.dvdrental.usecase.rental.cart;

import com.urassh.dvdrental.repository.LocalStore;

public class GetRentalCountUseCase {
    private final LocalStore localStore = LocalStore.shared;

    public int execute() {
        return localStore.getRentalCount();
    }
}
