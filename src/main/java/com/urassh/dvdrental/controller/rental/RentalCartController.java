package com.urassh.dvdrental.controller.rental;

import com.urassh.dvdrental.domain.Goods;
import com.urassh.dvdrental.usecase.members.GetMemberUseCase;
import com.urassh.dvdrental.usecase.rental.GetRentalCartUseCase;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class RentalCartController {
    @FXML
    private Label sumLabel;

    @FXML
    private VBox memberCard;

    @FXML
    private Label memberIdLabel;

    @FXML
    private Label memberNameLabel;

    @FXML
    private TextField searchField;

    private int sum = 0;
    private List<Goods> rentalCart = new ArrayList<>();
    private final BooleanProperty isFoundMember = new SimpleBooleanProperty(false);

    public void initialize() {
        memberCard.visibleProperty().bind(isFoundMember);

        setupRentalCart();

        searchField.setOnAction(event -> {
            final String keyword = searchField.getText().trim().toLowerCase();

            if (keyword.isEmpty()) {
                memberCard.setVisible(false);
                return;
            }

            new GetMemberUseCase().execute(keyword).thenAccept(member -> {
                if (member == null) {
                    Platform.runLater(() -> {
                        memberCard.setVisible(false);
                    });
                    return;
                }

                Platform.runLater(() -> {
                    isFoundMember.set(true);
                    memberIdLabel.setText(member.getId());
                    memberNameLabel.setText(member.getName());
                });
            });
        });
    }

    private void setupRentalCart() {
        rentalCart = new GetRentalCartUseCase().execute();

        for (Goods goods : rentalCart) {
            sum += goods.getFeeWithTax();
        }

        sumLabel.setText("合計 : " + sum + "円 (税込)");
    }


}
