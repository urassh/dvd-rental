package com.urassh.dvdrental.usecase.rental;

import com.urassh.dvdrental.infrastructure.LocalStore;

public class GetRentalCountUseCase {
    private final LocalStore localStore = LocalStore.shared;

    public int execute() {
        return localStore.getRentalCount();
    }
}
