package com.urassh.dvdrental.controller.rental;

import com.urassh.dvdrental.domain.Goods;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class RentalCartCellController {
    @FXML
    private Label goodsIdLabel;

    @FXML
    private Label goodsTitleLabel;

    @FXML
    private Label goodsPriceLabel;

    public void setGoods(Goods goods) {
        final String GOODS_ID = goods.getId();
        final String GOODS_TITLE = goods.getTitle();
        final int GOODS_PRICE = goods.getFee().getValue();

        goodsIdLabel.setText(GOODS_ID);
        goodsTitleLabel.setText(GOODS_TITLE);
        goodsPriceLabel.setText(GOODS_PRICE + "円");
    }
}
