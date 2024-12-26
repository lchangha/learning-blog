package com.project.learningblog.post.service.dto.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePostCommand {
    private String title;
    private long userId;
    private String content;
    private String category;

    private List<String> tags;
    private List<Byte[]> images;
}
