package com.project.learningblog.post.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePostRequest {
    private String title;
    private Long postId;
    private String content;
    private String category;

    private List<String> tags;
    private List<MultipartFile> images;
}
