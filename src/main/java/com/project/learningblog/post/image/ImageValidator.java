package com.project.learningblog.post.image;


import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ImageValidator {

    public void validate(MultipartFile file) {
        long maxSizeByte = 50 * 1024 * 1024;
        if (file.isEmpty()) {
            throw new IllegalArgumentException("파일이 비어 있습니다.");
        }
        if (!isValidMimeType(file)) {
            throw new IllegalArgumentException("유효하지 않은 이미지 파일입니다.");
        }
        if (file.getSize() > maxSizeByte) { 
            throw new IllegalArgumentException("파일 크기가 너무 큽니다.");
        }
    }

    private boolean isValidMimeType(MultipartFile file) {
        String mimeType = file.getContentType();
        return mimeType != null && mimeType.startsWith("image/");

    }
}
