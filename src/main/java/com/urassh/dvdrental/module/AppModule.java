package com.urassh.dvdrental.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.urassh.dvdrental.domain.interfaces.ImageRepository;
import com.urassh.dvdrental.repository.dummy.ImageDummyRepository;
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

        bind(ImageRepository.class).to(ImageDummyRepository.class).in(Singleton.class);

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