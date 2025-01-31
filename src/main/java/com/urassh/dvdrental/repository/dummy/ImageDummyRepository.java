package com.urassh.dvdrental.repository.dummy;

import com.urassh.dvdrental.domain.Member;
import com.urassh.dvdrental.domain.interfaces.ImageRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ImageDummyRepository implements ImageRepository {
    private static final String IMAGE_PATH = "src/main/resources/com/urassh/dvdrental/images/";
    private static final Path DEFAULT_ICON_PATH = Path.of(IMAGE_PATH, "default.png");

    @Override
    public byte[] get(Member member) {
        final String memberId = member.getId().toString();
        final String memberIconName = memberId + ".png";
        final Path memberImagePath = Path.of(IMAGE_PATH, memberIconName);

        if (Files.exists(memberImagePath)) return getMemberImage(memberIconName);
        return getDefaultImage();
    }

    private byte[] getMemberImage(String memberIconName) {
        final Path memberImagePath = Path.of(IMAGE_PATH, memberIconName);

        try {
            return Files.readAllBytes(memberImagePath);
        } catch (IOException error) {
            System.err.println("Error reading member image: " + error.getMessage());
            throw new RuntimeException(error);
        }
    }

    private byte[] getDefaultImage() {
        try {
            return Files.readAllBytes(DEFAULT_ICON_PATH);
        } catch (IOException error) {
            System.err.println("Error reading default image: " + error.getMessage());
            throw new RuntimeException(error);
        }
    }
}
