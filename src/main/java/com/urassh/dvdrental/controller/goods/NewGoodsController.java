package com.urassh.dvdrental.controller.goods;

import com.urassh.dvdrental.domain.Goods;
import com.urassh.dvdrental.usecase.goods.AddGoodsUseCase;
import com.urassh.dvdrental.util.Navigator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.ZoneId;
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

    @FXML
    protected void initialize() {
        addGoodsButton.setOnAction(event -> handleButtonClick(addGoodsButton));
        newGoods = Goods.newGoods();

        goodsIdField.setText(newGoods.getId());
    }

    private void handleButtonClick(Button clickedButton) {
        System.out.println("ボタンが押された");
        clickedButton.getStyleClass().remove("active");

        clickedButton.getStyleClass().add("active");

        String buttonId = clickedButton.getId();

        switch (buttonId) {
            case "addGoodsButton":
                newGoods = newGoods.setTitle(titleField.getText());
                newGoods = newGoods.setGenre(genreField.getText());
                LocalDate localDate = releaseDateField.getValue();
                if (localDate != null) {
                    Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                    System.out.println("選択された日付 (Date): " + date);
                    newGoods = newGoods.setReleaseDate(date);
                }
                newGoods = newGoods.setBelongToStore(belongToStoreField.getText());

                if (!newGoods.isAllDataExist()) {
                    System.out.println("入力に不備があります");
                    clickedButton.getStyleClass().remove("active");
                    break;
                }

                //モーダルを呼び出す

                final AddGoodsUseCase addGoods = new AddGoodsUseCase();
                addGoods.execute(newGoods);
                System.out.println("商品が追加されました");

                navigateTo(buttonId);
                break;
        }

    }

    private void navigateTo(String buttonId) {
        Navigator navigator = new Navigator(title.getScene());
        switch (buttonId) {
            case "addGoodsButton":
                navigator.navigateToGoods();
                break;
        }
    }
}
