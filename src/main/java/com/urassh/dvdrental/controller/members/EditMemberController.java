package com.urassh.dvdrental.controller.members;

import com.google.inject.Inject;
import com.urassh.dvdrental.domain.Member;
import com.urassh.dvdrental.usecase.members.UpdateMemberUseCase;
import com.urassh.dvdrental.util.DateExtension;
import com.urassh.dvdrental.util.Navigator;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Date;

public class EditMemberController {
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
    private Button updateMemberButton;

    private Member editedMember;

    private final Navigator navigator;
    private final UpdateMemberUseCase updateMemberUseCase;

    @Inject
    public EditMemberController(Navigator navigator, UpdateMemberUseCase updateMemberUseCase) {
        this.navigator = navigator;
        this.updateMemberUseCase = updateMemberUseCase;
    }

    @FXML
    protected void initialize() {
        updateMemberButton.setOnAction(event -> handleButtonClick());
    }

    public void setMember(Member member) {
        this.editedMember = member;
        memberIdField.setText(editedMember.getId().toString());
        nameField.setText(editedMember.getName());
        addressField.setText(editedMember.getAddress());
        phoneNumberField.setText(editedMember.getPhoneNumber());
        birthDateField.setValue(new DateExtension(editedMember.getBirthDate()).toLocalDate());
    }

    private void handleButtonClick() {
        final String inputName = nameField.getText();
        final String inputAddress = addressField.getText();
        final String inputPhoneNumber = phoneNumberField.getText();
        final Date inputBirthDate = new DateExtension().fromLocalDate(birthDateField.getValue());

        ErrorControlHighlight(nameField, inputName.isBlank());
        ErrorControlHighlight(addressField, inputAddress.isBlank());
        ErrorControlHighlight(phoneNumberField, inputPhoneNumber.isBlank());
        ErrorControlHighlight(birthDateField, inputBirthDate == null);

        if (inputName.isBlank()) return;
        if (inputAddress.isBlank()) return;
        if (inputPhoneNumber.isBlank()) return;
        if (inputBirthDate == null) return;

        editedMember = editedMember.setName(inputName);
        editedMember = editedMember.setAddress(inputAddress);
        editedMember = editedMember.setPhoneNumber(inputPhoneNumber);
        editedMember = editedMember.setBirthDate(inputBirthDate);

        updateMemberUseCase.execute(editedMember);
        navigator.navigateToMembers();
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
