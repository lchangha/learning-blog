package com.project.learningblog.post.image;

import com.project.learningblog.exception.ImageUploadException;
import com.project.learningblog.post.model.ImageFile;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


@Service
@AllArgsConstructor
public class ImageUploadManager {

    private final FileNameGenerator fileNameGenerator;
    private final FolderNameGenerator folderNameGenerator;
    private final StorageHandler storageHandler;
    private final ImageValidator imageValidator;


    public String handleUpload(ImageFile file) {
        try {
            imageValidator.validate(file);

            String[] nameArr = nameGenerate(file);

            String folderName = nameArr[0];
            String fileName = nameArr[1];


            Path folderPath = pathGenerate(folderName);
            return storageHandler.saveFile(file, folderPath, fileName);
        } catch (IOException e) {
            throw new ImageUploadException(e);
        }
    }

    private String[] nameGenerate(ImageFile file) {
        String folderName = folderNameGenerator.generateFolderNameByNow();
        String fileName = fileNameGenerator.generateFileName(file);
        return new String[] { folderName, fileName };
    }

    private Path pathGenerate(String path) {
        return Paths.get("images", path);
    }


}
