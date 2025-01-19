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
        newGoodsButton.setOnAction(event -> navigateToNew());
    }

    private void navigateToNew() {
        Navigator navigator = new Navigator(title.getScene());
        navigator.navigateToGoodsNew();
    }
}
