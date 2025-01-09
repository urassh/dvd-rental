package com.urassh.dvdrental.controller;

import com.urassh.dvdrental.util.Navigator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HomeController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        Navigator navigator = new Navigator(welcomeText.getScene());
        navigator.navigateToRental();
    }
}