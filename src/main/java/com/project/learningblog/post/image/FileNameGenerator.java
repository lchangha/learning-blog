package com.project.learningblog.post.image;


import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
public class FileNameGenerator {

    public String generateFileName(MultipartFile file) {
        String extension = getFileExtension(file.getOriginalFilename());
        return UUID.randomUUID().toString() + extension;
    }

    public String generateFolderName(LocalDate localDate) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return localDate.format(format);
    }

    private String getFileExtension(String filename) {
        return filename.substring(filename.lastIndexOf("."));
    }
}
