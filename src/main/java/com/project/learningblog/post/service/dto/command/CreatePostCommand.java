package com.project.learningblog.post.service.dto.command;

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
public class CreatePostCommand {
    private String title;
    private String userId;
    private String content;
    private String category;

    private List<String> tags;
    private List<byte[]> images;
}
