package com.urassh.dvdrental.controller.members;

import com.google.inject.Inject;
import com.urassh.dvdrental.domain.Member;
import com.urassh.dvdrental.usecase.members.GetAllMembersUseCase;
import com.urassh.dvdrental.util.Navigator;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MembersController {
    @FXML
    private Button newMemberButton;

    @FXML
    private ListView<Member> memberList;

    @FXML
    private ProgressIndicator loadingIndicator;

    @FXML
    private TextField searchField;

    private List<Member> allMembers = new ArrayList<>();
    private final BooleanProperty isLoading = new SimpleBooleanProperty(false);
    private final Navigator navigator;
    private final GetAllMembersUseCase getAllMembersUseCase;

    @Inject
    public MembersController(Navigator navigator, GetAllMembersUseCase getAllMembersUseCase) {
        this.navigator = navigator;
        this.getAllMembersUseCase = getAllMembersUseCase;
    }

    public void initialize() {
        memberList.setCellFactory(listView -> new MemberCell());
        loadingIndicator.visibleProperty().bind(isLoading);

        loadMembers();
        searchField.setOnAction(event -> filterMembers());

        memberList.setOnMouseClicked(event -> {
            final Member selectedMember = memberList.getSelectionModel().getSelectedItem();
            if (selectedMember == null) return;
            navigator.navigateToMemberDetail(selectedMember);
        });

        newMemberButton.setOnAction(event -> navigator.navigateToMembersNew());
    }

    private void loadMembers() {
        isLoading.set(true);

        getAllMembersUseCase.execute().thenAccept(members -> {
            Platform.runLater(() -> {
                allMembers = members;
                memberList.getItems().setAll(members);
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
            memberList.getItems().setAll(allMembers);
            return;
        }

        final Predicate<Member> matchesKeyword = member ->
                member.getName().toLowerCase().contains(keyword) ||
                member.getId().toString().toLowerCase().contains(keyword);

        List<Member> filteredMembers = allMembers.stream()
                .filter(matchesKeyword)
                .collect(Collectors.toList());

        memberList.getItems().setAll(filteredMembers);
    }
}