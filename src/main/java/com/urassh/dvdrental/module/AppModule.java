package com.urassh.dvdrental.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.urassh.dvdrental.domain.interfaces.GoodsRepository;
import com.urassh.dvdrental.domain.interfaces.MemberRepository;
import com.urassh.dvdrental.domain.interfaces.RentalRepository;
import com.urassh.dvdrental.infrastructure.GoodsDummyRepository;
import com.urassh.dvdrental.infrastructure.MemberDummyRepository;
import com.urassh.dvdrental.infrastructure.RentalDummyRepository;
import com.urassh.dvdrental.util.FxmlLoaderUtil;
import com.urassh.dvdrental.util.Navigator;
import javafx.stage.Stage;

public class AppModule extends AbstractModule {
    private final Stage primaryStage;

    public AppModule(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @Override
    protected void configure() {
        bind(Stage.class).toInstance(primaryStage);
        bind(FxmlLoaderUtil.class).asEagerSingleton();

        bind(GoodsRepository.class).to(GoodsDummyRepository.class).in(Singleton.class);
        bind(MemberRepository.class).to(MemberDummyRepository.class).in(Singleton.class);
        bind(RentalRepository.class).to(RentalDummyRepository.class).in(Singleton.class);

        install(new GoodsModule());
        install(new MemberModule());
        install(new RentalModule());
    }

    @Provides
    @Singleton
    public Navigator provideNavigator(Stage stage, FxmlLoaderUtil fxmlLoaderUtil) {
        return new Navigator(stage, fxmlLoaderUtil);
    }
}