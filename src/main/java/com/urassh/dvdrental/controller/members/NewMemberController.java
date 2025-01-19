package com.urassh.dvdrental.controller.members;

import com.google.inject.Inject;
import com.urassh.dvdrental.domain.Member;
import com.urassh.dvdrental.usecase.members.AddMemberUseCase;
import com.urassh.dvdrental.util.DateExtension;
import com.urassh.dvdrental.util.Navigator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.util.Date;

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

        if (inputName.isBlank()) return;
        if (inputAddress.isBlank()) return;
        if (inputBirthDate == null) return;

        newMember = newMember.setName(inputName);
        newMember = newMember.setAddress(inputAddress);
        newMember = newMember.setBirthDate(inputBirthDate);
        newMember = newMember.setPhoneNumber(inputPhoneNumber);

        addMemberUseCase.execute(newMember);
        navigator.navigateToMembers();
    }
}
