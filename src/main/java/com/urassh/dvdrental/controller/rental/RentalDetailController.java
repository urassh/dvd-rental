package com.urassh.dvdrental.controller.rental;

import com.google.inject.Inject;
import com.urassh.dvdrental.domain.Goods;
import com.urassh.dvdrental.usecase.rental.cart.AddToRentalCartUseCase;
import com.urassh.dvdrental.util.Navigator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.text.SimpleDateFormat;

public class RentalDetailController {
    @FXML
    private Label titleLabel;

    @FXML
    private Label idLabel;

    @FXML
    private Label genreLabel;

    @FXML
    private Label releaseDateLabel;

    @FXML
    private Label rentalCountLabel;

    @FXML
    private Label belongToStoreLabel;

    @FXML
    private Label isNewLabel;

    private Goods goods;

    private final Navigator navigator;

    @Inject
    public RentalDetailController(Navigator navigator) {
        this.navigator = navigator;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
        final String GOODS_DATE = new SimpleDateFormat("yyyy-MM-dd").format(goods.getReleaseDate());
        titleLabel.setText(goods.getTitle());
        idLabel.setText(goods.getId().toString());
        genreLabel.setText(goods.getGenre());
        releaseDateLabel.setText(GOODS_DATE);
        rentalCountLabel.setText(String.valueOf(goods.getLoanCount()));
        belongToStoreLabel.setText(goods.getBelongToStore());
        isNewLabel.setText(goods.isNew() ? "新作" : "旧作");
    }

    public void onAddToCart() {
        new AddToRentalCartUseCase().execute(goods);
        navigator.navigateToRental();
    }
}
