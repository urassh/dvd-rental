package com.urassh.dvdrental.controller.members;

import com.urassh.dvdrental.domain.Member;
import com.urassh.dvdrental.usecase.members.GetAllMembersUseCase;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;

public class MembersController {
    @FXML
    private ListView<Member> memberList;

    @FXML
    private ProgressIndicator loadingIndicator;

    private final BooleanProperty isLoading = new SimpleBooleanProperty(false);

    public void initialize() {
        memberList.setCellFactory(listView -> new MemberCell());
        loadingIndicator.visibleProperty().bind(isLoading);
        loadMembers();
    }

    private void loadMembers() {
        final GetAllMembersUseCase getAllMembers = new GetAllMembersUseCase();

        isLoading.set(true);

        getAllMembers.execute().thenAccept(members -> {
            Platform.runLater(() -> {
                memberList.getItems().setAll(members);
                isLoading.set(false);
            });
        }).exceptionally(ex -> {
            ex.printStackTrace();
            Platform.runLater(() -> isLoading.set(false)); // エラー時も非表示に
            return null;
        });
    }
}
