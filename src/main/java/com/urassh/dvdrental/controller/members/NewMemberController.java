package com.urassh.dvdrental.controller.members;

import com.google.inject.Inject;
import com.urassh.dvdrental.domain.Member;
import com.urassh.dvdrental.usecase.members.AddMemberUseCase;
import com.urassh.dvdrental.util.ConfirmationAlertUtil;
import com.urassh.dvdrental.util.DateExtension;
import com.urassh.dvdrental.util.Navigator;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Date;
import java.util.Optional;

public class NewMemberController {
    @FXML
    private TextField memberIdField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private DatePicker birthDateField;

    @FXML
    private Button addMemberButton;

    private Member newMember;

    private final Navigator navigator;
    private final AddMemberUseCase addMemberUseCase;

    @Inject
    public NewMemberController(Navigator navigator, AddMemberUseCase addMemberUseCase) {
        this.navigator = navigator;
        this.addMemberUseCase = addMemberUseCase;
    }

    @FXML
    protected void initialize() {
        addMemberButton.setOnAction(event -> handleButtonClick());
        newMember = Member.newMember();

        memberIdField.setText(newMember.getId().toString());
    }

    private void handleButtonClick() {
        final String inputName = nameField.getText();
        final String inputAddress = addressField.getText();
        final String inputPhoneNumber = phoneNumberField.getText();
        final Date inputBirthDate = new DateExtension().fromLocalDate(birthDateField.getValue());

        ErrorControlHighlight(nameField, inputName.isBlank());
        ErrorControlHighlight(addressField, inputAddress.isBlank());
        ErrorControlHighlight(phoneNumberField, !inputPhoneNumber.matches("\\d{3}-\\d{4}-\\d{4}"));
        ErrorControlHighlight(birthDateField, inputBirthDate == null);

        if (inputName.isBlank()) return;
        if (inputAddress.isBlank()) return;
        if (!inputPhoneNumber.matches("\\d{3}-\\d{4}-\\d{4}")) return;
        if (inputBirthDate == null) return;

        newMember = newMember.setName(inputName);
        newMember = newMember.setAddress(inputAddress);
        newMember = newMember.setBirthDate(inputBirthDate);
        newMember = newMember.setPhoneNumber(inputPhoneNumber);

        String content = "会員ID     : " + newMember.getId() + "\n氏名     : " + newMember.getName();
        ConfirmationAlertUtil alertUtil = new ConfirmationAlertUtil("会員追加", "会員情報を追加しますか？", content);

        Optional<ButtonType> result = alertUtil.showAndWait();

        if (result.isPresent() && result.get() == alertUtil.getAcceptButtonType()) {
            addMemberUseCase.execute(newMember);
            navigator.navigateToMembers();
        }
    }

    private void ErrorControlHighlight(Control control, boolean hasError) {
        String errorClass = "error";
        if (hasError) {
            if (!control.getStyleClass().contains(errorClass)) {
                control.getStyleClass().add(errorClass);
            }
        } else {
            control.getStyleClass().remove(errorClass);
        }
    }
}
