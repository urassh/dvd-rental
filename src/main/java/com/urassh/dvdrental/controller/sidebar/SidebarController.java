package com.urassh.dvdrental.controller.sidebar;

import com.urassh.dvdrental.util.Navigator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.Arrays;
import java.util.List;

public class SidebarController {
    @FXML
    private Label title;

    @FXML
    private Button rentalButton;

    @FXML
    private Button returnButton;

    @FXML
    private Button goodsButton;

    @FXML
    private Button membersButton;

    @FXML
    private List<Button> menuItems;

    @FXML
    protected void initialize() {
        menuItems = Arrays.asList(rentalButton, returnButton, goodsButton, membersButton);
        menuItems.forEach(button -> button.setOnAction(event -> handleButtonClick(button)));
    }

    private void handleButtonClick(Button clickedButton) {
        menuItems.forEach(button -> button.getStyleClass().remove("active"));

        clickedButton.getStyleClass().add("active");

        String buttonId = clickedButton.getId();

        navigateTo(buttonId);
    }

    private void navigateTo(String buttonId) {
        Navigator navigator = new Navigator(title.getScene());
        switch (buttonId) {
            case "rentalButton":
                navigator.navigateToRental();
                break;
            case "returnButton":
                navigator.navigateToReturn();
                break;
            case "goodsButton":
                navigator.navigateToGoods();
                break;
            case "membersButton":
                navigator.navigateToMembers();
                break;
        }
    }
}
