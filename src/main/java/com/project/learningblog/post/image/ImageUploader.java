package com.project.learningblog.post.image;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class ImageUploader {

    public String uploadFile(MultipartFile file, String targetPath) throws Exception {
        Path targetLocation = Paths.get(targetPath);
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        return targetLocation.toString();
    }

    public void createFolder(Path folder) {
        try {
            Files.createDirectories(folder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
