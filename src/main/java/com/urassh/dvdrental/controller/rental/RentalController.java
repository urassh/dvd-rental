package com.urassh.dvdrental.controller.rental;

import com.urassh.dvdrental.domain.Goods;
import com.urassh.dvdrental.usecase.goods.GetUnRentingGoodsUseCase;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;

public class RentalController {
    @FXML
    private ListView<Goods> rentalList;

    @FXML
    private ProgressIndicator loadingIndicator;

    private final BooleanProperty isLoading = new SimpleBooleanProperty(false);

    public void initialize() {
        rentalList.setCellFactory(listView -> new RentalCell());
        loadingIndicator.visibleProperty().bind(isLoading);
        loadRentals();
    }

    private void loadRentals() {
        final GetUnRentingGoodsUseCase getAllRentals = new GetUnRentingGoodsUseCase();

        isLoading.set(true);

        getAllRentals.execute().thenAccept(rentals -> {
            Platform.runLater(() -> {
                rentalList.getItems().setAll(rentals);
                isLoading.set(false);
            });
        }).exceptionally(ex -> {
            ex.printStackTrace();
            Platform.runLater(() -> isLoading.set(false)); // エラー時も非表示に
            return null;
        });
    }
}
