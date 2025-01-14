package com.urassh.dvdrental.controller.members;

import com.urassh.dvdrental.domain.Member;
import com.urassh.dvdrental.usecase.members.GetAllMembersUseCase;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class MembersController {
    @FXML
    private ListView<Member> memberList;

    public void initialize() {
        memberList.setCellFactory(listView -> new MemberCell());
        loadMembers();
    }

    private void loadMembers() {
        final GetAllMembersUseCase getAllMembers = new GetAllMembersUseCase();

        getAllMembers.execute().thenAccept(members -> {
            Platform.runLater(() -> {
                memberList.getItems().setAll(members);
            });
        }).exceptionally(ex -> {
            ex.printStackTrace();
            return null;
        });
    }
}
