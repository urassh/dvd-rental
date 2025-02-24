package com.urassh.dvdrental.controller.rental;

import com.urassh.dvdrental.domain.Goods;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.text.SimpleDateFormat;

public class RentalCellController {
    @FXML
    private Label goodsIdLabel;

    @FXML
    private Label goodsTitleLabel;

    @FXML
    private Label goodsReleaseDateLabel;
    
    @FXML
    private Label rentalGoodsNewLabel;

    public void setGoods(Goods goods) {
        final String GOODS_ID = goods.getId().toString();
        final String GOODS_TITLE = goods.getTitle();
        final String GOODS_DATE = new SimpleDateFormat("yyyy-MM-dd").format(goods.getReleaseDate());

        goodsIdLabel.setText(GOODS_ID);
        goodsTitleLabel.setText(GOODS_TITLE);
        goodsReleaseDateLabel.setText("発売 : " + GOODS_DATE);
        if(goods.isNew()) {
            rentalGoodsNewLabel.setVisible(true);
        } else {
            rentalGoodsNewLabel.setVisible(false);
        }
    }
}
