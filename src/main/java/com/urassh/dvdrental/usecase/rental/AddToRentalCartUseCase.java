package com.urassh.dvdrental.usecase.rental;

import com.urassh.dvdrental.domain.Goods;
import com.urassh.dvdrental.infrastructure.LocalStore;

public class AddToRentalCartUseCase {
    private final LocalStore localStore = new LocalStore();

    public void execute(Goods goods) {
        int count = localStore.getRentalCount();
        localStore.setRentalCount(count + 1);
    }
}
