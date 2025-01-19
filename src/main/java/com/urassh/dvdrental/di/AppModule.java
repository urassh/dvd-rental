package com.urassh.dvdrental.di;

import com.google.inject.AbstractModule;
import com.urassh.dvdrental.domain.interfaces.GoodsRepository;
import com.urassh.dvdrental.domain.interfaces.MemberRepository;
import com.urassh.dvdrental.domain.interfaces.RentalRepository;
import com.urassh.dvdrental.infrastructure.GoodsDummyRepository;
import com.urassh.dvdrental.infrastructure.MemberDummyRepository;
import com.urassh.dvdrental.infrastructure.RentalDummyRepository;
import com.urassh.dvdrental.usecase.goods.*;
import com.urassh.dvdrental.usecase.members.*;
import com.urassh.dvdrental.usecase.rental.AddRentalUseCase;
import com.urassh.dvdrental.usecase.rental.GetRentalsByMemberUseCase;
import com.urassh.dvdrental.usecase.rental.RemoveRentalUseCase;
import javafx.stage.Stage;

public class AppModule extends AbstractModule {
    private final Stage primaryStage;

    public AppModule(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @Override
    protected void configure() {
        bind(Stage.class).toInstance(primaryStage);
        bind(GoodsRepository.class).to(GoodsDummyRepository.class);
        bind(MemberRepository.class).to(MemberDummyRepository.class);
        bind(RentalRepository.class).to(RentalDummyRepository.class);

        //Goods UseCase
        bind(AddGoodsUseCase.class);
        bind(DeleteGoodsUseCase.class);
        bind(GetAllGoodsUseCase.class);
        bind(GetUnRentingGoodsUseCase.class);
        bind(UpdateGoodsUseCase.class);

        //Member UseCase
        bind(AddMemberUseCase.class);
        bind(DeleteMemberUseCase.class);
        bind(GetAllMembersUseCase.class);
        bind(GetMemberUseCase.class);
        bind(UpdateMemberUseCase.class);

        //Rental UseCase
        bind(AddRentalUseCase.class);
        bind(GetAllGoodsUseCase.class);
        bind(GetRentalsByMemberUseCase.class);
        bind(RemoveRentalUseCase.class);
    }
}