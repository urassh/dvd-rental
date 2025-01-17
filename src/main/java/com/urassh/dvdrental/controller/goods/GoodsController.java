package com.urassh.dvdrental.controller.goods;

import com.urassh.dvdrental.util.Navigator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class GoodsController {
    @FXML
    private Label title;

    @FXML
    private Button newGoodsButton;

    @FXML
    protected void initialize() {
        newGoodsButton.setOnAction(event -> handleButtonClick(newGoodsButton));
    }

    private void handleButtonClick(Button clickedButton) {
        clickedButton.getStyleClass().remove("active");

        clickedButton.getStyleClass().add("active");

        String buttonId = clickedButton.getId();

        navigateTo(buttonId);
    }

    private void navigateTo(String buttonId) {
        Navigator navigator = new Navigator(title.getScene());
        switch (buttonId) {
            case "newGoodsButton":
                navigator.navigateToGoodsNew();
                break;
        }
    }
}
