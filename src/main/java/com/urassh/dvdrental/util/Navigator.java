package com.urassh.dvdrental.util;

import com.urassh.dvdrental.RentalApp;
import com.urassh.dvdrental.controller.rental.RentalDetailController;
import com.urassh.dvdrental.domain.Goods;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Map;
import java.util.function.Consumer;

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
            "return/view.fxml",
            "rental/detail/view.fxml",
            "rental/cart/view.fxml"
    };

    private final Scene fromScene;

    public Navigator(Scene from) {
        this.fromScene = from;
    }

    private void navigateFxml(String fxmlPath, String title, Consumer<FXMLLoader> consumer) {
        try {
            if (fromScene != null) {
                fromScene.getWindow().hide();
            }

            FXMLLoader fxmlLoader = new FXMLLoader(RentalApp.class.getResource(fxmlPath));
            Scene scene = new Scene(fxmlLoader.load(), WINDOW_SIZE.get("width"), WINDOW_SIZE.get("height"));
            Stage stage = new Stage();

            if (consumer != null) {
                consumer.accept(fxmlLoader);
            }

            stage.setScene(scene);
            stage.setTitle(appTitle(title));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void navigateFxml(String fxmlPath, String title) {
        navigateFxml(fxmlPath, title, null);
    }

    public void navigateToHome() {
        navigateFxml(FXML_PATHS[0], "Home");
    }

    public void navigateToGoods() {
        navigateFxml(FXML_PATHS[1], "商品情報");
    }

    public void navigateToMembers() {
        navigateFxml(FXML_PATHS[2], "会員情報");
    }

    public void navigateToRental() {
        navigateFxml(FXML_PATHS[3], "貸し出し");
    }

    public void navigateToReturn() {
        navigateFxml(FXML_PATHS[4], "返却");
    }

    public void navigateToRentalDetail(Goods goods) {
        navigateFxml(FXML_PATHS[5], goods.getTitle(), fxmlLoader -> {
            RentalDetailController controller = fxmlLoader.getController();
            controller.setGoods(goods);
        });
    }

    public void navigateToRentalCart() {
        navigateFxml(FXML_PATHS[6], "商品カート");
    }

    private String appTitle(String title) {
        return "DVD貸し出し管理アプリ - " + title;
    }
}
