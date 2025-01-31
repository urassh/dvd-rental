package com.urassh.dvdrental.module;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.urassh.dvdrental.domain.interfaces.GoodsRepository;
import com.urassh.dvdrental.infrastructure.gateway.GoodsGatewayImpl;
import com.urassh.dvdrental.repository.dummy.GoodsDummyRepository;
import com.urassh.dvdrental.repository.interfaces.GoodsGateway;
import com.urassh.dvdrental.usecase.goods.*;

public class GoodsModule extends AbstractModule {
    @Override
    protected void configure() {
        // Gateway
        bind(GoodsGateway.class).to(GoodsGatewayImpl.class).in(Singleton.class);

        // Repository
        bind(GoodsRepository.class).to(GoodsDummyRepository.class).in(Singleton.class);

        // UseCase
        bind(AddGoodsUseCase.class).in(Singleton.class);
        bind(DeleteGoodsUseCase.class).in(Singleton.class);
        bind(GetAllGoodsUseCase.class).in(Singleton.class);
        bind(GetUnRentingGoodsUseCase.class).in(Singleton.class);
        bind(UpdateGoodsUseCase.class).in(Singleton.class);
    }
}