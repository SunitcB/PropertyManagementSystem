package com.miu.waafinalproject.service;

import com.miu.waafinalproject.model.ResponseModel;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface FileStorageService {
    public void init();

    ResponseModel save(MultipartFile file, String propertyId);

    public UrlResource load(String filename);

    ResponseModel deleteAll();
    ResponseModel deleteFile(String fileName) throws IOException;

    public Stream<Path> loadAll();

}
