package com.project.learningblog.post.image;


import com.project.learningblog.post.model.ImageFile;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
@NoArgsConstructor
public class FileNameGenerator {

    public String generateFileName(ImageFile file) {
        String extension = getFileExtension(file.getName());
        return UUID.randomUUID().toString() + extension;
    }

    private String getFileExtension(String filename) {
        return filename.substring(filename.lastIndexOf("."));
    }
}
