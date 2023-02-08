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

    public String imageToBase64() throws IOException {
        String absolutePath = context.getRealPath("resources/static");
        byte[] fileContent = FileUtils.readFileToByteArray(new File("/Users/mannsu/Projects/WAAFinalProject/src/main/resources/static/img.jpg"));
        return Base64.getEncoder().encodeToString(fileContent);
    }
}
