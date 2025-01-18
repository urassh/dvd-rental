package com.urassh.dvdrental.controller.members;

import com.urassh.dvdrental.domain.Member;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MemberCellController {
    @FXML
    private Label memberIdLabel;

    @FXML
    private Label memberNameLabel;

    public void setMember(Member member) {
        memberIdLabel.setText(member.getId().toString());
        memberNameLabel.setText(member.getName() + " 様");
    }
}
