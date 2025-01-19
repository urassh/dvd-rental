package com.urassh.dvdrental;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.urassh.dvdrental.module.AppModule;
import com.urassh.dvdrental.util.FxmlLoaderUtil;
import com.urassh.dvdrental.util.Navigator;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class RentalApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        final Injector injector = Guice.createInjector(new AppModule(stage));
        final FxmlLoaderUtil fxmlLoaderUtil = new FxmlLoaderUtil(injector);
        final Navigator navigator = new Navigator(stage, fxmlLoaderUtil);
        navigator.navigateToHome();
    }

    public static void main(String[] args) {
        launch();
    }
}