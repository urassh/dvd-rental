package com.urassh.dvdrental.controller.rental;

import com.urassh.dvdrental.RentalApp;
import com.urassh.dvdrental.domain.Goods;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.function.Consumer;

public class RentalCartCell extends ListCell<Goods> {
    private StackPane content;
    private RentalCartCellController controller;
    private Consumer<Goods> cancelAction;

    public RentalCartCell(Consumer<Goods> cancelAction) {
        try {
            FXMLLoader loader = new FXMLLoader(RentalApp.class.getResource("rental/cart/cell/component.fxml"));
            this.content = loader.load();
            this.controller = loader.getController();
            this.cancelAction = cancelAction;
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
            controller.setGoods(goods, cancelAction);
            setGraphic(content);
        }
    }
}
