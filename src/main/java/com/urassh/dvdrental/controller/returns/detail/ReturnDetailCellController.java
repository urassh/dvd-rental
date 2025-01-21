package com.urassh.dvdrental.controller.returns.detail;

import com.urassh.dvdrental.domain.Rental;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.function.Consumer;

public class ReturnDetailCellController {
    @FXML
    private Label goodsIdLabel;

    @FXML
    private Label goodsTitleLabel;

    @FXML
    private Label goodsPriceLabel;

    @FXML
    private StackPane card;

    public void setRental(Rental rental, Consumer<Rental> onChangedReturn) {
        final String GOODS_ID = rental.getGoods().getId().toString();
        final String GOODS_TITLE = rental.getGoods().getTitle();
        final int LATE_PRICE = rental.getLateFee().getValue();

        goodsIdLabel.setText(GOODS_ID);
        goodsTitleLabel.setText(GOODS_TITLE);
        goodsPriceLabel.setText("延滞料金 : " + LATE_PRICE + "円");
        card.setOnMouseClicked( event -> {
            onChangedReturn.accept(rental);

            if (card.getStyleClass().contains("selected")) {
                card.getStyleClass().remove("selected");
            } else {
                card.getStyleClass().add("selected");
            }
        });
    }
}
