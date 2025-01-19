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
    private Label title;

    @FXML
    private ListView<Member> memberList;

    @FXML
    private ProgressIndicator loadingIndicator;

    @FXML
    private TextField searchField;

    @FXML
    private Button newMemberButton;

    private final BooleanProperty isLoading = new SimpleBooleanProperty(false);
    private List<Member> allMembers = new ArrayList<>();
    private final GetAllMembersUseCase getAllMembersUseCase;

    @Inject
    public MembersController(GetAllMembersUseCase getAllMembersUseCase) {
        this.getAllMembersUseCase = getAllMembersUseCase;
    }

    public void initialize() {
        newMemberButton.setOnAction(event -> navigateToNew());

        memberList.setCellFactory(listView -> new MemberCell());
        loadingIndicator.visibleProperty().bind(isLoading);
        loadMembers();

        searchField.setOnAction(event -> filterMembers());
    }

    private void navigateToNew() {
        Navigator navigator = new Navigator(title.getScene());
        navigator.navigateToMembersNew();
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