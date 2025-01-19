package com.urassh.dvdrental.di;

import com.google.inject.AbstractModule;
import com.urassh.dvdrental.domain.interfaces.GoodsRepository;
import com.urassh.dvdrental.domain.interfaces.MemberRepository;
import com.urassh.dvdrental.domain.interfaces.RentalRepository;
import com.urassh.dvdrental.infrastructure.GoodsDummyRepository;
import com.urassh.dvdrental.infrastructure.MemberDummyRepository;
import com.urassh.dvdrental.infrastructure.RentalDummyRepository;
import com.urassh.dvdrental.usecase.goods.AddGoodsUseCase;
import com.urassh.dvdrental.usecase.goods.GetAllGoodsUseCase;

public class AppModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(GoodsRepository.class).to(GoodsDummyRepository.class);
        bind(MemberRepository.class).to(MemberDummyRepository.class);
        bind(RentalRepository.class).to(RentalDummyRepository.class);

        bind(AddGoodsUseCase.class);
        bind(GetAllGoodsUseCase.class);
    }
}