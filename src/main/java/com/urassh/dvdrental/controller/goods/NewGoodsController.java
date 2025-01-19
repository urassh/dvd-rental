package com.urassh.dvdrental.controller.goods;

import com.google.inject.Inject;
import com.urassh.dvdrental.domain.Goods;
import com.urassh.dvdrental.usecase.goods.AddGoodsUseCase;
import com.urassh.dvdrental.util.DateExtension;
import com.urassh.dvdrental.util.Navigator;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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

    private final Navigator navigator;
    private final AddGoodsUseCase addGoodsUseCase;

    @Inject
    public NewGoodsController(Navigator navigator, AddGoodsUseCase addGoodsUseCase) {
        this.navigator = navigator;
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

        ErrorControlHighlight(titleField, inputTitle.isBlank());
        ErrorControlHighlight(genreField, inputGenre.isBlank());
        ErrorControlHighlight(releaseDateField, inputReleaseDate == null);
        ErrorControlHighlight(belongToStoreField, inputBelongToStore.isBlank());

        if (inputTitle.isBlank()) return;
        if (inputGenre.isBlank()) return;
        if (inputReleaseDate == null) return;
        if (inputBelongToStore.isBlank()) return;

        newGoods = newGoods.setTitle(inputTitle);
        newGoods = newGoods.setGenre(inputGenre);
        newGoods = newGoods.setReleaseDate(inputReleaseDate);
        newGoods = newGoods.setBelongToStore(belongToStoreField.getText());

        addGoodsUseCase.execute(newGoods);
        navigator.navigateToGoods();
    }

    private void ErrorControlHighlight(Control control, boolean hasError) {
        String errorClass = "error";
        if (hasError) {
            if (!control.getStyleClass().contains(errorClass)) {
                control.getStyleClass().add(errorClass);
            }
        } else {
            control.getStyleClass().remove(errorClass);
        }
    }
}
