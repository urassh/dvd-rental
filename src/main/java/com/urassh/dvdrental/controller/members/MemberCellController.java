package com.urassh.dvdrental.controller.members;

import com.urassh.dvdrental.domain.Member;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public class MemberCellController {
    @FXML
    private Label memberIdLabel;

    @FXML
    private Label memberNameLabel;
    
    @FXML
    private ImageView memberImageView;

    public void setMember(Member member, Image memberImage) {
        memberIdLabel.setText(member.getId().toString());
        memberNameLabel.setText(member.getName() + " 様");
        memberImageView.setImage(memberImage);
        Circle circle = new Circle(30);
        circle.setCenterX(30);
        circle.setCenterY(30);
        memberImageView.setClip(circle);
    }
}
