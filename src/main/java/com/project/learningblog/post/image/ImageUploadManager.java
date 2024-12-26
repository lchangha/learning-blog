package com.project.learningblog.post.image;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;


@Service
@AllArgsConstructor
public class ImageUploadManager {

    private final FileNameGenerator fileNameGenerator;
    private final ImageUploader imageUploader;
    private final ImageValidator imageValidator;


    public String handleUpload(MultipartFile file) throws Exception {

        imageValidator.validate(file);

        String folderName = fileNameGenerator.generateFolderName(LocalDate.now());
        String fileName = fileNameGenerator.generateFileName(file);


        Path folderPath = Paths.get("images", folderName);
        imageUploader.createFolder(folderPath);


        String newFilePath = folderPath.resolve(fileName).toString();
        return imageUploader.uploadFile(file, newFilePath);
    }
}
