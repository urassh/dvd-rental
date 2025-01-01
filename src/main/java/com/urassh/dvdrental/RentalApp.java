package com.urassh.dvdrental;

import com.urassh.dvdrental.util.Navigator;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class RentalApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Navigator navigator = new Navigator(stage.getScene());
        navigator.navigateToHome();
    }

    public static void main(String[] args) {
        launch();
    }
}