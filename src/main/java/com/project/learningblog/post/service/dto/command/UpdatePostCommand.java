package com.project.learningblog.post.service.dto.command;

import com.project.learningblog.post.model.ImageFile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePostCommand implements Command {
    private String title;
    private Long postId;
    private String content;
    private String category;

    private List<String> tags;
    private List<ImageFile> images;
}
