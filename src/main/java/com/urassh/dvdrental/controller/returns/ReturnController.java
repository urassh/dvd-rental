package com.urassh.dvdrental.controller.returns;

import com.google.inject.Inject;
import com.urassh.dvdrental.domain.Member;
import com.urassh.dvdrental.domain.Rental;
import com.urassh.dvdrental.usecase.rental.GetAllRentalsUseCase;
import com.urassh.dvdrental.util.Navigator;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ReturnController {
    @FXML
    private ListView<Rental> rentalMemberList;

    @FXML
    private ProgressIndicator loadingIndicator;

    @FXML
    private TextField searchField;

    private final BooleanProperty isLoading = new SimpleBooleanProperty(false);
    private List<Rental> rentals = new ArrayList<>();
    private final Navigator navigator;
    private final GetAllRentalsUseCase getAllRentalsUseCase;

    @Inject
    public ReturnController(Navigator navigator, GetAllRentalsUseCase getAllRentalsUseCase) {
        this.navigator = navigator;
        this.getAllRentalsUseCase = getAllRentalsUseCase;
    }

    public void initialize() {
        rentalMemberList.setCellFactory(listView -> new ReturnCell());
        loadingIndicator.visibleProperty().bind(isLoading);
        loadRentals();

        searchField.setOnAction(event -> filterMembers());

        rentalMemberList.setOnMouseClicked(event -> {
            final Rental selectedRental = rentalMemberList.getSelectionModel().getSelectedItem();
            if (selectedRental == null) return;

            navigator.navigateToReturnDetail(selectedRental);
        });
    }

    private List<Rental> getRentingMembers(List<Rental> rentals) {
        return rentals.stream()
                .collect(Collectors.groupingBy(Rental::getMember))
                .values()
                .stream()
                .map(list -> list.get(0))
                .collect(Collectors.toList());
    }

    private void loadRentals() {
        isLoading.set(true);

        getAllRentalsUseCase.execute().thenAccept(rentals -> {
            final List<Rental> rentingMembers = getRentingMembers(rentals);

            Platform.runLater(() -> {
                this.rentals = rentals;
                rentalMemberList.getItems().setAll(rentingMembers);
                isLoading.set(false);
            });
        }).exceptionally(ex -> {
            ex.printStackTrace();
            Platform.runLater(() -> isLoading.set(false)); // エラー時も非表示に
            return null;
        });
    }

    private void filterMembers() {
        final String keyword = searchField.getText().trim().toLowerCase();

        if (keyword.isEmpty()) {
            rentalMemberList.getItems().setAll(rentals);
            return;
        }

        final Predicate<Member> matchesKeyword = member ->
                member.getName().toLowerCase().contains(keyword) ||
                member.getId().toString().toLowerCase().contains(keyword);

        List<Rental> filteredRentals = rentals.stream()
                .filter(rental -> matchesKeyword.test(rental.getMember()))
                .toList();

        rentalMemberList.getItems().setAll(filteredRentals);
    }
}