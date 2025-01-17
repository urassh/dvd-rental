package com.urassh.dvdrental.controller.rental;

import com.urassh.dvdrental.domain.Goods;
import com.urassh.dvdrental.domain.Member;
import com.urassh.dvdrental.domain.Money;
import com.urassh.dvdrental.usecase.members.GetMemberUseCase;
import com.urassh.dvdrental.usecase.rental.*;
import com.urassh.dvdrental.util.Navigator;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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

    @FXML
    private ListView<Goods> rentalCartView;

    private Money sum = Money.ZERO;
    private List<Goods> rentalCart = new ArrayList<>();
    private Member rentingMember;
    private final BooleanProperty isFoundMember = new SimpleBooleanProperty(false);

    public void initialize() {
        memberCard.visibleProperty().bind(isFoundMember);

        setupRentalCart();
        loadCarts();

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
                    rentingMember = member;
                    isFoundMember.set(true);
                    memberIdLabel.setText(member.getId());
                    memberNameLabel.setText(member.getName());
                });
            });
        });
    }

    public void onConfirmRental() {
        if (rentingMember == null) return;
        if (rentalCart.isEmpty()) return;

        for (Goods goods : rentalCart) {
            new AddRentalUseCase().execute(goods, rentingMember);
        }

        new ClearRentalCartUseCase().execute();

        Navigator navigator = new Navigator(sumLabel.getScene());
        navigator.navigateToRental();
    }

    private void setupRentalCart() {
        rentalCartView.setCellFactory(listView -> new RentalCartCell(this::onCancelRental));
        rentalCart = new GetRentalCartUseCase().execute();

        for (Goods goods : rentalCart) {
            sum = sum.add(goods.getFee());
        }

        sumLabel.setText("合計 : " + sum.withTax().getValue() + "円 (税込)");
    }

    private void loadCarts() {
        List<Goods> rentalCart = new GetRentalCartUseCase().execute();

        sum = Money.ZERO;
        for (Goods goods : rentalCart) {
            sum = sum.add(goods.getFee());
        }
        sumLabel.setText("合計 : " + sum.withTax().getValue() + "円 (税込)");

        rentalCartView.getItems().setAll(rentalCart);
    }

    private void onCancelRental(Goods goods) {
        new RemoveFromCartUseCase().execute(goods);
        loadCarts();
    }
}
