package com.project.learningblog.advice;

import com.project.learningblog.exception.ImageUploadException;
import com.project.learningblog.post.controller.dto.ApiResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ImageUploadException.class)
    public ApiResponse<Void> handleImageUploadException(ImageUploadException ex) {
        return ApiResponse.error(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ApiResponse<Void> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ApiResponse.error(ex.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ApiResponse<Void> handleIllegalArgumentException(EntityNotFoundException ex) {
        return ApiResponse.error(ex.getMessage());
    }
}
