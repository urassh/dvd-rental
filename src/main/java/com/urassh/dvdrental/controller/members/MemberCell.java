package com.urassh.dvdrental.controller.members;

import com.urassh.dvdrental.RentalApp;
import com.urassh.dvdrental.domain.Member;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class MemberCell extends ListCell<Member> {
    private StackPane content;
    private MemberCellController controller;

    public MemberCell() {
        try {
            FXMLLoader loader = new FXMLLoader(RentalApp.class.getResource("members/member_cell/component.fxml"));
            content = loader.load();
            controller = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void updateItem(Member member, boolean empty) {
        super.updateItem(member, empty);
        if (empty || member == null) {
            setGraphic(null);
        } else {
            controller.setMember(member);
            setGraphic(content);
        }
    }
}
