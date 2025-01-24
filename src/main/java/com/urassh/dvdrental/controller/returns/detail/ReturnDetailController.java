package com.urassh.dvdrental.controller.returns.detail;

import com.google.inject.Inject;
import com.urassh.dvdrental.domain.Money;
import com.urassh.dvdrental.domain.Rental;
import com.urassh.dvdrental.usecase.rental.GetRentalsByMemberUseCase;
import com.urassh.dvdrental.usecase.returns.ReturnUseCase;
import com.urassh.dvdrental.util.Navigator;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ReturnDetailController {
    @FXML
    private Label lateFeeLabel;

    @FXML
    private Label memberIdLabel;

    @FXML
    private Label memberNameLabel;

    @FXML
    private ProgressIndicator loadingIndicator;

    @FXML
    private ListView<Rental> rentingGoodsView;

    private Money sumLateFee;
    private final BooleanProperty isLoading = new SimpleBooleanProperty(false);
    private final List<Rental> rentalsWithMember = new ArrayList<>();
    private final List<Rental> returnTargets = new ArrayList<>();
    private final GetRentalsByMemberUseCase getRentalsByMemberUseCase;
    private final ReturnUseCase returnUseCase;
    private final Navigator navigator;

    @Inject
    public ReturnDetailController(
            Navigator navigator,
            GetRentalsByMemberUseCase getRentalsByMemberUseCase,
            ReturnUseCase returnUseCase) {
        this.navigator = navigator;
        this.getRentalsByMemberUseCase = getRentalsByMemberUseCase;
        this.returnUseCase = returnUseCase;
    }

    public void initialize() {
        loadingIndicator.visibleProperty().bind(isLoading);
    }

    public void setRental(Rental rental) {
        memberIdLabel.setText(rental.getMember().getId().toString());
        memberNameLabel.setText(rental.getMember().getName());
        lateFeeLabel.setText("延滞料金合計 : 0円 (税込)");

        isLoading.set(true);

        getRentalsByMemberUseCase.execute(rental.getMember())
            .thenAccept(rentals -> {
                synchronized (rentalsWithMember) {
                    rentalsWithMember.addAll(rentals);
                }
            })
            .thenRun(() -> {
                Platform.runLater(() -> {
                    rentingGoodsView.setCellFactory(listView -> new ReturnDetailCell(onChangedReturn()));
                    rentingGoodsView.getItems().setAll(rentalsWithMember);
                    isLoading.set(false);
                });
            });
    }

    public void onConfirmReturn() {
        if (returnTargets.isEmpty()) return;

        for (Rental rental : returnTargets) {
            returnUseCase.execute(rental);
        }

        navigator.navigateToReturn();
    }

    private Consumer<Rental> onChangedReturn() {
        return rental -> {
            if (returnTargets.contains(rental)) {
                returnTargets.remove(rental);
            } else {
                returnTargets.add(rental);
                sumLateFee = returnTargets.stream()
                        .map(Rental::getLateFee)
                        .reduce(Money.ZERO, Money::add);

                lateFeeLabel.setText("延滞料金合計 : " + sumLateFee.withTax().getValue() + "円 (税込)");
            }
        };
    }
}
