package com.project.learningblog.post.image;

import com.project.learningblog.exception.ImageUploadException;
import com.project.learningblog.post.model.ImageFile;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;


@Service
@AllArgsConstructor
public class ImageUploadManager {

    private final FileNameGenerator fileNameGenerator;
    private final FolderNameGenerator folderNameGenerator;
    private final FileStorageHandler fileStorageHandler;
    private final ImageValidator imageValidator;


    public String handleUpload(ImageFile file) {
        try {
            imageValidator.validate(file);

            String folderName = folderNameGenerator.generateFolderNameByNow();
            String fileName = fileNameGenerator.generateFileName(file);

            Path folderPath = Paths.get("images", folderName);

            return fileStorageHandler.saveFile(file, folderPath, fileName);
        } catch (IOException e) {
            throw new ImageUploadException(e);
        }
    }


}
