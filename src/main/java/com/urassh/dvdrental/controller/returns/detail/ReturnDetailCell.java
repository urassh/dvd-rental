package com.urassh.dvdrental.controller.returns.detail;

import com.urassh.dvdrental.RentalApp;
import com.urassh.dvdrental.domain.Rental;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.layout.StackPane;
import java.io.IOException;
import java.util.function.Consumer;

public class ReturnDetailCell extends ListCell<Rental> {
    private StackPane content;
    private ReturnDetailCellController controller;
    private Consumer<Rental> onChangedReturn;

    public ReturnDetailCell(Consumer<Rental> onChangedReturn) {
        try {
            FXMLLoader loader = new FXMLLoader(RentalApp.class.getResource("return/detail/cell/component.fxml"));
            this.content = loader.load();
            this.controller = loader.getController();
            this.onChangedReturn = onChangedReturn;
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
            controller.setRental(rental, onChangedReturn);
            setGraphic(content);
        }
    }
}
