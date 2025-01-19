package com.urassh.dvdrental.controller.goods;

import com.google.inject.Inject;
import com.urassh.dvdrental.domain.Goods;
import com.urassh.dvdrental.usecase.goods.UpdateGoodsUseCase;
import com.urassh.dvdrental.util.DateExtension;
import com.urassh.dvdrental.util.Navigator;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EditGoodsController {
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
    private Button updateGoodsButton;

    private Goods editedGoods;

    private final Navigator navigator;
    private final UpdateGoodsUseCase updateGoodsUseCase;

    @Inject
    public EditGoodsController(Navigator navigator, UpdateGoodsUseCase updateGoodsUseCase) {
        this.navigator = navigator;
        this.updateGoodsUseCase = updateGoodsUseCase;
    }

    @FXML
    protected void initialize() {
        updateGoodsButton.setOnAction(event -> handleButtonClick());
    }

    public void setGoods(Goods goods) {
        this.editedGoods = goods;
        titleField.setText(editedGoods.getTitle());
        goodsIdField.setText(editedGoods.getId().toString());
        genreField.setText(editedGoods.getGenre());
        releaseDateField.setValue(new DateExtension(editedGoods.getReleaseDate()).toLocalDate());
        belongToStoreField.setText(editedGoods.getBelongToStore());
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

        editedGoods = editedGoods.setTitle(inputTitle);
        editedGoods = editedGoods.setGenre(inputGenre);
        editedGoods = editedGoods.setReleaseDate(inputReleaseDate);
        editedGoods = editedGoods.setBelongToStore(belongToStoreField.getText());

        updateGoodsUseCase.execute(editedGoods);
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
