package com.urassh.dvdrental.controller.home;

import com.google.inject.Inject;
import com.urassh.dvdrental.util.Navigator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HomeController {
    @FXML
    public Button button;

    private final Navigator navigator;

    @Inject
    public HomeController(Navigator navigator) {
        this.navigator = navigator;
    }

    @FXML
    protected void initialize() {
        button.setOnAction(event -> onHelloButtonClick());
    }

    private void onHelloButtonClick() {
        navigator.navigateToRental();
    }
}
