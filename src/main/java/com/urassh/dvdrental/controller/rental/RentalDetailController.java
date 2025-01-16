package com.urassh.dvdrental.controller.rental;

import com.urassh.dvdrental.domain.Goods;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class RentalDetailController {
    @FXML
    private Label titleLabel;

    @FXML
    private Label idLabel;

    public void setGoods(Goods goods) {
        titleLabel.setText(goods.getTitle());
        idLabel.setText(goods.getId());
    }
}
