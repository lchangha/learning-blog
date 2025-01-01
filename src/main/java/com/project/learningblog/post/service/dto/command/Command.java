package com.project.learningblog.post.service.dto.command;

import com.project.learningblog.post.model.ImageFile;

import java.util.List;

public interface Command {
    List<ImageFile> getImages();
}
