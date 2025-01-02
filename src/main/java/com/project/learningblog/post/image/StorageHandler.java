package com.project.learningblog.post.image;

import com.project.learningblog.post.model.ImageFile;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Component
@NoArgsConstructor
public class StorageHandler {

    public String saveFile(ImageFile file,Path folder , String fileName) throws IOException  {
        createFolder(folder);
        Path targetPath = folder.resolve(fileName);
        Files.copy(file.getContent(), targetPath, StandardCopyOption.REPLACE_EXISTING);
        return targetPath.toString();
    }

    public void createFolder(Path folder) throws IOException {
            Files.createDirectories(folder);
    }
}
