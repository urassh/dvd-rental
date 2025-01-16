package com.urassh.dvdrental.controller.members;

import com.urassh.dvdrental.domain.Member;
import com.urassh.dvdrental.usecase.members.GetAllMembersUseCase;
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

public class MembersController {
    @FXML
    private ListView<Member> memberList;

    @FXML
    private ProgressIndicator loadingIndicator;

    @FXML
    private TextField searchField;

    private final BooleanProperty isLoading = new SimpleBooleanProperty(false);
    private List<Member> allMembers = new ArrayList<>();

    public void initialize() {
        memberList.setCellFactory(listView -> new MemberCell());
        loadingIndicator.visibleProperty().bind(isLoading);
        loadMembers();

        searchField.setOnAction(event -> filterMembers());
    }

    private void loadMembers() {
        final GetAllMembersUseCase getAllMembers = new GetAllMembersUseCase();

        isLoading.set(true);

        getAllMembers.execute().thenAccept(members -> {
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
                member.getId().toLowerCase().contains(keyword);

        List<Member> filteredMembers = allMembers.stream()
                .filter(matchesKeyword)
                .collect(Collectors.toList());

        memberList.getItems().setAll(filteredMembers);
    }
}