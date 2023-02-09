package com.miu.waafinalproject.service.impl;

import com.miu.waafinalproject.model.ResponseModel;
import com.miu.waafinalproject.service.FileStorageService;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class FileStorageServiceImpl implements FileStorageService {
    private ResponseModel responseModel;
    private final Path root = Paths.get("uploads");
    private UrlResource defaultResource;
    FileStorageServiceImpl(){
        Path defaultFile = root.resolve("img.jpg");
        try {
            defaultResource = new UrlResource(defaultFile.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void init() {
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public ResponseModel save(MultipartFile file, String propertyId) {
        responseModel = new ResponseModel();
        try {
            Files.copy(file.getInputStream(), this.root.resolve(propertyId + ".jpg"));
            responseModel.setMessage("Property images uploaded successfully.");
            responseModel.setStatus(HttpStatus.OK);
        } catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                throw new RuntimeException("A file of that name already exists.");
            }
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return responseModel;
    }

    @Override
    public UrlResource load(String filename) {
        try {
            Path file = root.resolve(filename + ".jpg");
            UrlResource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                return defaultResource;
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public ResponseModel deleteAll() {
//        FileSystemUtils.deleteRecursively(root.toFile());
        return null;
    }

    @Override
    public ResponseModel deleteFile(String fileName) throws IOException {
        responseModel = new ResponseModel();

        UrlResource tobeDeleled = load(fileName + ".jpg");
        tobeDeleled.getFile().delete();
        responseModel.setStatus(HttpStatus.OK);
        responseModel.setMessage("The property image has been deleted.");

        return responseModel;
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }
}
