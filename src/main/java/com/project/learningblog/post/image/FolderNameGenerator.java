package com.project.learningblog.post.image;

import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class FolderNameGenerator {
    private final Clock clock;

    public FolderNameGenerator() {
        this.clock = Clock.systemDefaultZone();
    }

    public String generateFolderNameByNow() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return LocalDate.now(clock).format(format);
    }
}
