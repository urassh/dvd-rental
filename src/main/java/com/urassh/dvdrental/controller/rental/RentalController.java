package com.urassh.dvdrental.controller.rental;

import com.google.inject.Inject;
import com.urassh.dvdrental.domain.Goods;
import com.urassh.dvdrental.usecase.goods.GetUnRentingGoodsUseCase;
import com.urassh.dvdrental.usecase.rental.cart.GetRentalCountUseCase;
import com.urassh.dvdrental.util.Navigator;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class RentalController {
    @FXML
    private Label rentalCountLabel;

    @FXML
    private ListView<Goods> rentalList;

    @FXML
    private ProgressIndicator loadingIndicator;

    @FXML
    private TextField searchField;

    @FXML
    private StackPane cart;

    private List<Goods> rentalGoods = new ArrayList<>();
    private final BooleanProperty isLoading = new SimpleBooleanProperty(false);
    private final Navigator navigator;
    private final GetUnRentingGoodsUseCase getUnRentingGoodsUseCase;

    @Inject
    public RentalController(Navigator navigator, GetUnRentingGoodsUseCase getUnRentingGoodsUseCase) {
        this.navigator = navigator;
        this.getUnRentingGoodsUseCase = getUnRentingGoodsUseCase;
    }

    public void initialize() {
        final int rentalCount = new GetRentalCountUseCase().execute();
        rentalCountLabel.setText(String.valueOf(rentalCount));

        rentalList.setCellFactory(listView -> new RentalCell());
        loadingIndicator.visibleProperty().bind(isLoading);
        loadRentals();
        searchField.setOnAction(event -> filterGoods());

        rentalList.setOnMouseClicked(event -> {
            final Goods selectedGoods = rentalList.getSelectionModel().getSelectedItem();
            if (selectedGoods == null) {
                return;
            }

            navigator.navigateToRentalDetail(selectedGoods);
        });

        cart.setOnMouseClicked(event -> {
            navigator.navigateToRentalCart();
        });
    }

    private void loadRentals() {
        isLoading.set(true);

        getUnRentingGoodsUseCase.execute().thenAccept(rentals -> {
            Platform.runLater(() -> {
                rentalGoods = rentals;
                rentalList.getItems().setAll(rentals);
                isLoading.set(false);
            });
        }).exceptionally(ex -> {
            ex.printStackTrace();
            Platform.runLater(() -> isLoading.set(false)); // エラー時も非表示に
            return null;
        });
    }

    private void filterGoods() {
        final String keyword = searchField.getText().trim().toLowerCase();

        if (keyword.isEmpty()) {
            rentalList.getItems().setAll(rentalGoods);
            return;
        }

        final Predicate<Goods> matchesKeyword = good ->
                good.getTitle().toLowerCase().contains(keyword) ||
                good.getId().toString().toLowerCase().contains(keyword);

        List<Goods> filteredMembers = rentalGoods.stream()
                .filter(matchesKeyword)
                .collect(Collectors.toList());

        rentalList.getItems().setAll(filteredMembers);
    }
}
