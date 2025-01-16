package com.urassh.dvdrental.usecase.rental;

import com.urassh.dvdrental.infrastructure.LocalStore;

public class GetRentalCountUseCase {
    private final LocalStore localStore = new LocalStore();

    public int execute() {
        return localStore.getRentalCount();
    }
}
