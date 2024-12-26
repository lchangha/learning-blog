package com.project.learningblog.post.controller.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {
    private String title;
    private String author;
    private String content;
    private String category;
    private List<String> tags;
    private List<String> imagePaths;
}
