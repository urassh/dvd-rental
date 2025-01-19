package com.urassh.dvdrental.controller.returns.detail;

import com.google.inject.Inject;
import com.urassh.dvdrental.domain.Money;
import com.urassh.dvdrental.domain.Rental;
import com.urassh.dvdrental.usecase.rental.GetRentalsByMemberUseCase;
import com.urassh.dvdrental.usecase.rental.RemoveRentalUseCase;
import com.urassh.dvdrental.util.Navigator;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import java.util.ArrayList;
import java.util.List;

public class ReturnDetailController {
    @FXML
    private Label lateFeeLabel;

    @FXML
    private Label memberIdLabel;

    @FXML
    private Label memberNameLabel;

    @FXML
    private ListView<Rental> rentingGoodsView;

    private Money sumLateFee;
    private final List<Rental> rentalsWithMember = new ArrayList<>();
    private final List<Rental> returnTargets = new ArrayList<>();
    private final GetRentalsByMemberUseCase getRentalsByMemberUseCase;
    private final RemoveRentalUseCase removeRentalUseCase;
    private final Navigator navigator;

    @Inject
    public ReturnDetailController(
            Navigator navigator,
            GetRentalsByMemberUseCase getRentalsByMemberUseCase,
            RemoveRentalUseCase removeRentalUseCase) {
        this.navigator = navigator;
        this.getRentalsByMemberUseCase = getRentalsByMemberUseCase;
        this.removeRentalUseCase = removeRentalUseCase;
    }

    public void setRental(Rental rental) {
        memberIdLabel.setText(rental.getMember().getId().toString());
        memberNameLabel.setText(rental.getMember().getName());

        getRentalsByMemberUseCase.execute(rental.getMember())
            .thenAccept(rentals -> {
                synchronized (rentalsWithMember) {
                    rentalsWithMember.addAll(rentals);
                }

                sumLateFee = rentals.stream()
                        .map(Rental::getLateFee)
                        .reduce(Money.ZERO, Money::add);
            })
            .thenRun(() -> {
                Platform.runLater(() -> {
                    lateFeeLabel.setText("遅延料金 : " + sumLateFee.withTax().getValue() + "円 (税込)");
                    rentingGoodsView.setCellFactory(listView -> new ReturnDetailCell());
                    rentingGoodsView.getItems().setAll(rentalsWithMember);
                });
            });
    }

    public void onConfirmReturn() {
        if (returnTargets.isEmpty()) return;

        for (Rental rental : returnTargets) {
            removeRentalUseCase.execute(rental);
        }

        navigator.navigateToReturn();
    }
}