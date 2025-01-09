package com.urassh.dvdrental.controller;

import com.urassh.dvdrental.util.Navigator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SidebarController {
    @FXML
    private Label title;

    @FXML
    protected void navigateToRentalButton() {
        Navigator navigator = new Navigator(title.getScene());
        navigator.navigateToRental();
    }

    @FXML
    protected void navigateToMembersButton() {
        Navigator navigator = new Navigator(title.getScene());
        navigator.navigateToMembers();
    }

    @FXML
    protected void navigateToGoodsButton() {
        Navigator navigator = new Navigator(title.getScene());
        navigator.navigateToGoods();
    }

    @FXML
    protected void navigateToReturnButton() {
        Navigator navigator = new Navigator(title.getScene());
        navigator.navigateToReturn();
    }
}
