package com.miu.waafinalproject.utils;

import jakarta.servlet.ServletContext;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

@RequiredArgsConstructor
@Component
public class PropertyImageUtil {
    private final ServletContext context;

    public String imageToBase64(File file) throws IOException {
        byte[] fileContent = FileUtils.readFileToByteArray(file);
        return Base64.getEncoder().encodeToString(fileContent);
    }
}
