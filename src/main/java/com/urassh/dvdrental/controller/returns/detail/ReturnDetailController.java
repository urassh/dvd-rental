package com.urassh.dvdrental.controller.returns.detail;

import com.google.inject.Inject;
import com.urassh.dvdrental.domain.Money;
import com.urassh.dvdrental.domain.Rental;
import com.urassh.dvdrental.usecase.rental.GetRentalsByMemberUseCase;
import com.urassh.dvdrental.usecase.returns.ReturnUseCase;
import com.urassh.dvdrental.util.Navigator;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
    private ListView<Rental> rentingGoodsView;

    private Money sumLateFee;
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

    public void setRental(Rental rental) {
        memberIdLabel.setText(rental.getMember().getId().toString());
        memberNameLabel.setText(rental.getMember().getName());

        getRentalsByMemberUseCase.execute(rental.getMember())
            .thenAccept(rentals -> {
                synchronized (rentalsWithMember) {
                    rentalsWithMember.addAll(rentals);
                }
            })
            .thenRun(() -> {
                Platform.runLater(() -> {
                    lateFeeLabel.setText("遅延料金 : " + sumLateFee.withTax().getValue() + "円 (税込)");
                    rentingGoodsView.setCellFactory(listView -> new ReturnDetailCell(onChangedReturn()));
                    rentingGoodsView.getItems().setAll(rentalsWithMember);
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
            }
        };
    }
}