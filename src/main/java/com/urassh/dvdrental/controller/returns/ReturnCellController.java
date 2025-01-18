package com.urassh.dvdrental.controller.returns;

import com.urassh.dvdrental.domain.Rental;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ReturnCellController {
    @FXML
    private Label memberIdLabel;

    @FXML
    private Label memberNameLabel;

    public void setRental(Rental rental) {
        memberIdLabel.setText(rental.getMember().getId().toString());
        memberNameLabel.setText(rental.getMember().getName() + " 様");
    }
}
