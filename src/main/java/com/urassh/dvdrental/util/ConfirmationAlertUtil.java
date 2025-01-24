package com.urassh.dvdrental.util;

import javafx.scene.control.*;

public class ConfirmationAlertUtil extends Alert {
    private ButtonType acceptButtonType = new ButtonType("確定", ButtonBar.ButtonData.OK_DONE);
    private ButtonType cancelButtonType = new ButtonType("キャンセル", ButtonBar.ButtonData.CANCEL_CLOSE);
    private Button acceptButton;
    private final DialogPane dialogPane;

    public ConfirmationAlertUtil(String title, String header, String content) {
        super(AlertType.CONFIRMATION);
        setTitle(title);
        setHeaderText(header);
        setContentText(content);

        getButtonTypes().setAll(acceptButtonType, cancelButtonType);

        dialogPane = getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("/com/urassh/dvdrental/util/alert.css").toExternalForm());
        dialogPane.getStyleClass().add("ios-alert");
        acceptButton = (Button) dialogPane.lookupButton(acceptButtonType);
        acceptButton.getStyleClass().add("confirm-button");
    }

    public ButtonType getAcceptButtonType() {
        return acceptButtonType;
    }

    public void setAcceptButtonText(String text) {
        acceptButtonType = new ButtonType(text, ButtonBar.ButtonData.OK_DONE);
        getButtonTypes().setAll(acceptButtonType, cancelButtonType);
        acceptButton = (Button) dialogPane.lookupButton(acceptButtonType);
        acceptButton.getStyleClass().add("confirm-button");
    }

    public void acceptIsDanger() {
        acceptButton.getStyleClass().add("confirm-button");
        acceptButton.getStyleClass().add("danger");
    }

    public void setCancelButtonText(String text) {
        cancelButtonType = new ButtonType(text, ButtonBar.ButtonData.CANCEL_CLOSE);
        getButtonTypes().setAll(acceptButtonType, cancelButtonType);
    }
}
