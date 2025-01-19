package com.urassh.dvdrental.controller.returns;

import com.urassh.dvdrental.RentalApp;
import com.urassh.dvdrental.domain.Rental;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class ReturnCell extends ListCell<Rental> {
    private StackPane content;
    private ReturnCellController controller;

    public ReturnCell() {
        try {
            FXMLLoader loader = new FXMLLoader(RentalApp.class.getResource("return/return_cell/component.fxml"));
            content = loader.load();
            controller = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void updateItem(Rental rental, boolean empty) {
        super.updateItem(rental, empty);
        if (empty || rental == null) {
            setGraphic(null);
        } else {
            controller.setRental(rental);
            setGraphic(content);
        }
    }
}
