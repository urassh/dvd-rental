package com.urassh.dvdrental.controller.goods;

import com.urassh.dvdrental.RentalApp;
import com.urassh.dvdrental.domain.Goods;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class GoodsCell extends ListCell<Goods> {
    private StackPane content;
    private GoodsCellController controller;

    public GoodsCell() {
        try {
            FXMLLoader loader = new FXMLLoader(RentalApp.class.getResource("goods/cell/component.fxml"));
            content = loader.load();
            controller = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void updateItem(Goods goods, boolean empty) {
        super.updateItem(goods, empty);
        if (empty || goods == null) {
            setGraphic(null);
        } else {
            controller.setGoods(goods);
            setGraphic(content);
        }
    }
}
