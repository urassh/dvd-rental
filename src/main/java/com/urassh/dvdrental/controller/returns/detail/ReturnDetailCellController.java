package com.urassh.dvdrental.controller.returns.detail;

import com.urassh.dvdrental.domain.Rental;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ReturnDetailCellController {
    @FXML
    private Label goodsIdLabel;

    @FXML
    private Label goodsTitleLabel;

    @FXML
    private Label goodsPriceLabel;

    public void setRental(Rental rental) {
        final String GOODS_ID = rental.getGoods().getId().toString();
        final String GOODS_TITLE = rental.getGoods().getTitle();
        final int LATE_PRICE = rental.getLateFee().getValue();

        goodsIdLabel.setText(GOODS_ID);
        goodsTitleLabel.setText(GOODS_TITLE);
        goodsPriceLabel.setText("延滞料金 : " + LATE_PRICE + "円");
    }
}
