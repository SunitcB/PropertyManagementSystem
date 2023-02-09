package com.miu.waafinalproject;

import com.miu.waafinalproject.service.FileStorageService;
import jakarta.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WaaFinalProjectApplication implements CommandLineRunner {
    @Resource
    FileStorageService storageService;
    public static void main(String[] args) {
        SpringApplication.run(WaaFinalProjectApplication.class, args);
    }

    @Override
    public void run(String... arg) throws Exception {
//    storageService.deleteAll();
        storageService.init();
    }
}
