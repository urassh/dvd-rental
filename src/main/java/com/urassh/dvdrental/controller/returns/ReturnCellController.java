package com.urassh.dvdrental.controller.returns;

import com.urassh.dvdrental.domain.Member;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ReturnCellController {
    @FXML
    private Label memberIdLabel;

    @FXML
    private Label memberNameLabel;

    public void setMember(Member member) {
        memberIdLabel.setText(member.getId());
        memberNameLabel.setText(member.getName() + " 様");
    }
}
