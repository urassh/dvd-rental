package com.urassh.dvdrental.controller.members;

import com.urassh.dvdrental.domain.Member;
import com.urassh.dvdrental.usecase.members.AddMemberUseCase;
import com.urassh.dvdrental.util.DateExtension;
import com.urassh.dvdrental.util.Navigator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Date;

public class NewMemberController {
    @FXML
    private Label title;

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

    @FXML
    protected void initialize() {
        addMemberButton.setOnAction(event -> handleButtonClick());
        newMember = Member.newMember();

        memberIdField.setText(newMember.getId().toString());
    }

    private void handleButtonClick() {
        final String inputName = nameField.getText();
        final String inputAddress = addressField.getText();
        final int inputPhoneNumber;
        try {
            inputPhoneNumber = Integer.parseInt(phoneNumberField.getText());
        } catch (final NumberFormatException e) {
            return;
        }
        final Date inputBirthDate = new DateExtension().fromLocalDate(birthDateField.getValue());

        if (inputName.isBlank()) return;
        if (inputAddress.isBlank()) return;
        if (inputBirthDate == null) return;

        newMember = newMember.setName(inputName);
        newMember = newMember.setAddress(inputAddress);
        newMember = newMember.setBirthDate(inputBirthDate);
        newMember = newMember.setPhoneNumber(inputPhoneNumber);

        new AddMemberUseCase().execute(newMember);
        Navigator navigator = new Navigator(title.getScene());
        navigator.navigateToMembers();
    }
}
