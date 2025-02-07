package com.project.learningblog.post.service.dto.command;

import com.project.learningblog.post.model.ImageFile;

import java.util.List;

public interface SupportsImages {
    List<ImageFile> getImages();
}
