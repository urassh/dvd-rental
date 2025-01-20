package com.urassh.dvdrental.controller.members;

import com.google.inject.Inject;
import com.urassh.dvdrental.domain.Member;
import com.urassh.dvdrental.usecase.members.DeleteMemberUseCase;
import com.urassh.dvdrental.util.Navigator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.text.SimpleDateFormat;

public class MemberDetailController {
    @FXML
    private Label nameLabel;

    @FXML
    private Label idLabel;

    @FXML
    private Label addressLabel;

    @FXML
    private Label phoneNumberLabel;

    @FXML
    private Label birthDateLabel;

    private Member member;

    private final Navigator navigator;
    private final DeleteMemberUseCase deleteMemberUseCase;

    @Inject
    public MemberDetailController(Navigator navigator, DeleteMemberUseCase deleteMemberUseCase) {
        this.navigator = navigator;
        this.deleteMemberUseCase = deleteMemberUseCase;
    }

    public void setMember(Member member) {
        this.member = member;
        final String MEMBER_DATE = new SimpleDateFormat("yyyy-MM-dd").format(member.getBirthDate());
        nameLabel.setText(member.getName());
        idLabel.setText(member.getId().toString());
        addressLabel.setText(member.getAddress());
        phoneNumberLabel.setText(member.getPhoneNumber());
        birthDateLabel.setText(MEMBER_DATE);
    }

    public void onEdit() {
    }

    public void onRemove() {
        deleteMemberUseCase.execute(member);
        navigator.navigateToMembers();
    }
}
