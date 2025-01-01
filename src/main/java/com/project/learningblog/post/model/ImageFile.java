package com.project.learningblog.post.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.InputStream;

@AllArgsConstructor
@Getter
public class ImageFile {
    private String name;
    private InputStream content;
    private String contentType;
    private long size;

    public boolean isEmpty() {
        return content == null || size == 0L;
    }
}
