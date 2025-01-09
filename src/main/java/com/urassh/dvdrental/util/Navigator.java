package com.urassh.dvdrental.util;

import com.urassh.dvdrental.RentalApp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Map;

public class Navigator {
    private static final Map<String, Integer> WINDOW_SIZE = Map.of(
            "width", 1440,
            "height", 1024
    );
    private static final String[] FXML_PATHS = {
            "home/view.fxml",
            "goods/view.fxml",
            "members/view.fxml",
            "rental/view.fxml",
            "return/view.fxml"
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
            Scene scene = new Scene(fxmlLoader.load(), WINDOW_SIZE.get("width"), WINDOW_SIZE.get("height"));
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

    public void navigateToGoods() {
        navigateFxml(FXML_PATHS[1], "Goods");
    }

    public void navigateToMembers() {
        navigateFxml(FXML_PATHS[2], "Members");
    }

    public void navigateToRental() {
        navigateFxml(FXML_PATHS[3], "Rental");
    }

    public void navigateToReturn() {
        navigateFxml(FXML_PATHS[4], "Return");
    }

    private String appTitle(String title) {
        return "DVD貸し出し管理アプリ - " + title;
    }
}
