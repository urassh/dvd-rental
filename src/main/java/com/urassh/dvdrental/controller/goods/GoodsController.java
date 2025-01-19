package com.urassh.dvdrental.controller.goods;

import com.google.inject.Inject;
import com.urassh.dvdrental.controller.rental.RentalCell;
import com.urassh.dvdrental.domain.Goods;
import com.urassh.dvdrental.usecase.goods.GetAllGoodsUseCase;
import com.urassh.dvdrental.util.Navigator;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GoodsController {
    @FXML
    private Button newGoodsButton;

    @FXML
    private ListView<Goods> goodsList;

    @FXML
    private ProgressIndicator loadingIndicator;

    @FXML
    private TextField searchField;

    private List<Goods> goods = new ArrayList<>();
    private final BooleanProperty isLoading = new SimpleBooleanProperty(false);
    private final Navigator navigator;
    private final GetAllGoodsUseCase getAllGoodsUseCase;

    @Inject
    public GoodsController(Navigator navigator, GetAllGoodsUseCase getAllGoodsUseCase) {
        this.navigator = navigator;
        this.getAllGoodsUseCase = getAllGoodsUseCase;
    }

    @FXML
    protected void initialize() {
        goodsList.setCellFactory(listView -> new RentalCell());
        loadingIndicator.visibleProperty().bind(isLoading);

        loadGoods();
        searchField.setOnAction(event -> filterGoods());

        goodsList.setOnMouseClicked(event -> {
            final Goods selectedGoods = goodsList.getSelectionModel().getSelectedItem();
            if (selectedGoods == null) return;
            navigator.navigateToGoodsDetail(selectedGoods);
        });

        newGoodsButton.setOnAction(event -> navigator.navigateToGoodsNew());
    }

    private void loadGoods() {
        isLoading.set(true);

        getAllGoodsUseCase.execute().thenAccept(result -> {
            Platform.runLater(() -> {
                goods = result;
                goodsList.getItems().setAll(result);
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
            goodsList.getItems().setAll(goods);
            return;
        }

        final Predicate<Goods> matchesKeyword = good ->
                good.getTitle().toLowerCase().contains(keyword) ||
                good.getId().toString().toLowerCase().contains(keyword);

        List<Goods> filteredMembers = goods.stream()
                .filter(matchesKeyword)
                .collect(Collectors.toList());

        goodsList.getItems().setAll(filteredMembers);
    }
}
