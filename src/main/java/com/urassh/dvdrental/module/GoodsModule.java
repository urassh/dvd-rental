package com.urassh.dvdrental.module;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.urassh.dvdrental.usecase.goods.*;

public class GoodsModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(AddGoodsUseCase.class).in(Singleton.class);
        bind(DeleteGoodsUseCase.class).in(Singleton.class);
        bind(GetAllGoodsUseCase.class).in(Singleton.class);
        bind(GetUnRentingGoodsUseCase.class).in(Singleton.class);
        bind(UpdateGoodsUseCase.class).in(Singleton.class);
    }
}