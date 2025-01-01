package com.urassh.dvdrental.util;

import com.urassh.dvdrental.RentalApp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Navigator {
    private static final String[] FXML_PATHS = {
            "home/view.fxml",
            "result/view.fxml"
    };

    private final Scene fromScene;

    public Navigator(Scene from) {
        this.fromScene = from;
    }

    private void navigateFxml(String fxmlPath, String title) {
        try {
            if (fromScene != null) {
                fromScene.getWindow().hide();
            }

            FXMLLoader fxmlLoader = new FXMLLoader(RentalApp.class.getResource(fxmlPath));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.setTitle(appTitle(title));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void navigateToHome() {
        navigateFxml(FXML_PATHS[0], "Home");
    }

    public void navigateToResult() {
        navigateFxml(FXML_PATHS[1], "Result");
    }

    private String appTitle(String title) {
        return "Gacha App - " + title;
    }
}
