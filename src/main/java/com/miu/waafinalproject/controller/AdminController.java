package com.miu.waafinalproject.controller;

import com.miu.waafinalproject.model.ResponseModel;
import com.miu.waafinalproject.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdminController {
    private ResponseModel responseModel;
    private final AdminService adminService;

    @PatchMapping("/approveUser/{id}")
    public ResponseEntity<ResponseModel> approveUser(@PathVariable long id) {
        responseModel = adminService.approveSignup(id);
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }
}
