package com.urassh.dvdrental.controller.goods;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.urassh.dvdrental.di.AppModule;
import com.urassh.dvdrental.domain.Goods;
import com.urassh.dvdrental.usecase.goods.AddGoodsUseCase;
import com.urassh.dvdrental.util.DateExtension;
import com.urassh.dvdrental.util.Navigator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Date;

public class NewGoodsController {
    @FXML
    private Label title;

    @FXML
    private TextField goodsIdField;

    @FXML
    private TextField titleField;

    @FXML
    private TextField genreField;

    @FXML
    private DatePicker releaseDateField;

    @FXML
    private TextField belongToStoreField;

    @FXML
    private Button addGoodsButton;

    private Goods newGoods;

    private final AddGoodsUseCase addGoodsUseCase;

    @Inject
    public NewGoodsController(AddGoodsUseCase addGoodsUseCase) {
        this.addGoodsUseCase = addGoodsUseCase;
    }

    @FXML
    protected void initialize() {
        addGoodsButton.setOnAction(event -> handleButtonClick());
        newGoods = Goods.newGoods();

        goodsIdField.setText(newGoods.getId().toString());
    }

    private void handleButtonClick() {
        final String inputTitle = titleField.getText();
        final String inputGenre = genreField.getText();
        final Date inputReleaseDate = new DateExtension().fromLocalDate(releaseDateField.getValue());
        final String inputBelongToStore = belongToStoreField.getText();

        if (inputTitle.isBlank()) return;
        if (inputGenre.isBlank()) return;
        if (inputReleaseDate == null) return;
        if (inputBelongToStore.isBlank()) return;

        newGoods = newGoods.setTitle(inputTitle);
        newGoods = newGoods.setGenre(inputGenre);
        newGoods = newGoods.setReleaseDate(inputReleaseDate);
        newGoods = newGoods.setBelongToStore(belongToStoreField.getText());

        addGoodsUseCase.execute(newGoods);
        Navigator navigator = new Navigator(title.getScene());
        navigator.navigateToGoods();
    }
}
