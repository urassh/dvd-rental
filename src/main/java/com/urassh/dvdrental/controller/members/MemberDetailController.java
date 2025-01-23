package com.urassh.dvdrental.controller.members;

import com.google.inject.Inject;
import com.urassh.dvdrental.domain.Member;
import com.urassh.dvdrental.usecase.members.DeleteMemberUseCase;
import com.urassh.dvdrental.util.ConfirmationAlertUtil;
import com.urassh.dvdrental.util.Navigator;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.text.SimpleDateFormat;
import java.util.Optional;

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
        navigator.navigateToMemberEdit(member);
    }

    public void onRemove() {
        String content = "会員ID     : " + member.getId() + "\n氏名     : " + member.getName();
        ConfirmationAlertUtil alertUtil = new ConfirmationAlertUtil("会員削除", "会員情報を削除しますか？", content);
        alertUtil.setAcceptButtonText("削除");
        alertUtil.acceptIsDanger();

        Optional<ButtonType> result = alertUtil.showAndWait();

        if (result.isPresent() && result.get() == alertUtil.getAcceptButtonType()) {
            deleteMemberUseCase.execute(member);
            navigator.navigateToMembers();
        }
    }
}
