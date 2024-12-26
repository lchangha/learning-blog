package com.project.learningblog.post.service.dto.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostResult {
    private String title;
    private String author;
    private String content;
    private String category;
    private List<String> tags;
    private List<String> imagePaths;
}
