package com.urassh.dvdrental.usecase.members;

import com.google.inject.Inject;
import com.urassh.dvdrental.domain.Member;
import com.urassh.dvdrental.domain.interfaces.ImageRepository;
import com.urassh.dvdrental.infrastructure.dummy.ImageDummyRepository;
import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;

public class GetMemberIconUseCase {
    private final ImageRepository imageRepository = new ImageDummyRepository();

    public Image execute(Member member) {
        return new Image(new ByteArrayInputStream(imageRepository.get(member)));
    }
}
