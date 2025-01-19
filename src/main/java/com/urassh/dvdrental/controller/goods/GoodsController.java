package com.urassh.dvdrental.controller.goods;

import com.google.inject.Inject;
import com.urassh.dvdrental.util.Navigator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GoodsController {
    @FXML
    private Button newGoodsButton;

    private final Navigator navigator;

    @Inject
    public GoodsController(Navigator navigator) {
        this.navigator = navigator;
    }

    @FXML
    protected void initialize() {
        newGoodsButton.setOnAction(event -> navigateToNew());
    }

    private void navigateToNew() {
        navigator.navigateToGoodsNew();
    }
}
