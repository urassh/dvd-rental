package com.urassh.dvdrental.module;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.urassh.dvdrental.usecase.rental.AddRentalUseCase;
import com.urassh.dvdrental.usecase.rental.GetRentalsByMemberUseCase;
import com.urassh.dvdrental.usecase.returns.ReturnUseCase;

public class RentalModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(AddRentalUseCase.class).in(Singleton.class);
        bind(GetRentalsByMemberUseCase.class).in(Singleton.class);
        bind(ReturnUseCase.class).in(Singleton.class);
    }
}