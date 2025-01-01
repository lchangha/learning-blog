package com.project.learningblog.post.image;


import com.project.learningblog.post.model.ImageFile;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@NoArgsConstructor
public class ImageValidator {
    private final long MAX_SIZE_BYTES = 50 * 1024 * 1024;

    public void validate(ImageFile file) {
        validateEmpty(file);
        validateMimeType(file);
        validateSize(file);
    }

    private void validateEmpty(ImageFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("파일이 비어 있습니다.");
        }
    }

    private void validateMimeType(ImageFile file) {
        String mimeType = file.getContentType();
        if (mimeType != null && mimeType.startsWith("image/")) {
            throw new IllegalArgumentException("유효하지 않은 이미지 파일 입니다");
        }
    }

    private void validateSize(ImageFile file) {
        if (file.getSize() > MAX_SIZE_BYTES) {
            throw new IllegalArgumentException("파일 크기가 너무 큽니다.");
        }
    }
}
