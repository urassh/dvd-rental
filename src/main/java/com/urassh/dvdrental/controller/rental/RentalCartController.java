package com.urassh.dvdrental.controller.rental;

import com.google.inject.Inject;
import com.urassh.dvdrental.domain.Goods;
import com.urassh.dvdrental.domain.Member;
import com.urassh.dvdrental.domain.Money;
import com.urassh.dvdrental.usecase.members.GetMemberUseCase;
import com.urassh.dvdrental.usecase.rental.*;
import com.urassh.dvdrental.usecase.rental.cart.ClearRentalCartUseCase;
import com.urassh.dvdrental.usecase.rental.cart.GetRentalCartUseCase;
import com.urassh.dvdrental.usecase.rental.cart.RemoveFromCartUseCase;
import com.urassh.dvdrental.util.ConfirmationAlertUtil;
import com.urassh.dvdrental.util.Navigator;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    private ProgressIndicator loadingIndicator;

    @FXML
    private Label memberNotFoundLabel;

    @FXML
    private TextField searchField;

    @FXML
    private ListView<Goods> rentalCartView;

    private Money sum = Money.ZERO;
    private List<Goods> rentalCart = new ArrayList<>();
    private Member rentingMember;
    private final BooleanProperty isFoundMember = new SimpleBooleanProperty(false);

    private final BooleanProperty isLoading = new SimpleBooleanProperty(false);
    private final Navigator navigator;
    private final GetMemberUseCase getMemberUseCase;
    private final AddRentalUseCase addRentalUseCase;

    @Inject
    public RentalCartController(Navigator navigator, GetMemberUseCase getMemberUseCase, AddRentalUseCase addRentalUseCase) {
        this.navigator = navigator;
        this.getMemberUseCase = getMemberUseCase;
        this.addRentalUseCase = addRentalUseCase;
    }

    public void initialize() {
        loadingIndicator.visibleProperty().bind(isLoading);
        memberCard.visibleProperty().bind(isFoundMember);
        memberNotFoundLabel.visibleProperty().bind(Bindings.not(isFoundMember));

        setupRentalCart();
        loadCarts();

        searchField.setOnAction(event -> {
            final String keyword = searchField.getText().trim().toLowerCase();
            ErrorControlHighlight(searchField, false);

            if (keyword.isEmpty()) {
                isFoundMember.set(false);
                ErrorControlHighlight(searchField, true);
                return;
            }

            isLoading.set(true);
            final UUID memberId = UUID.fromString(keyword);

            getMemberUseCase.execute(memberId).thenAccept(member -> {
                if (member == null) {
                    Platform.runLater(() -> {
                        isFoundMember.set(false);
                        ErrorControlHighlight(searchField, true);
                        isLoading.set(false);
                    });
                    return;
                }

                Platform.runLater(() -> {
                    rentingMember = member;
                    isFoundMember.set(true);
                    memberIdLabel.setText(member.getId().toString());
                    memberNameLabel.setText(member.getName());
                    isLoading.set(false);
                });
            });
        });
    }

    public void onConfirmRental() {
        ErrorControlHighlight(searchField, rentingMember == null);
        if (rentingMember == null) return;
        if (rentalCart.isEmpty()) return;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("貸出会員ID : ").append(rentingMember.getId()).append("\n氏名     : ").append(rentingMember.getName()).append("\n");
        stringBuilder.append("----------------------------------\n\n");
        for (Goods goods : rentalCart) {
            stringBuilder.append(goods.getTitle()).append("\n");
        }
        stringBuilder.append("\n----------------------------------\n");
        stringBuilder.append(rentalCart.size()).append(" 点\n");
        stringBuilder.append("合計 : ").append(sum.withTax().getValue()).append("円 (税込)");

        String content = stringBuilder.toString();
        ConfirmationAlertUtil alertUtil = new ConfirmationAlertUtil("貸出", "貸出操作を確定しますか？", content);

        Optional<ButtonType> result = alertUtil.showAndWait();

        if (result.isPresent() && result.get() == alertUtil.getAcceptButtonType()) {
            for (Goods goods : rentalCart) {
                addRentalUseCase.execute(goods, rentingMember);
            }
            new ClearRentalCartUseCase().execute();
            navigator.navigateToRental();
        }
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
