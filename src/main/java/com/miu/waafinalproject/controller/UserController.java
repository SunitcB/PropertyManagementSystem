package com.miu.waafinalproject.controller;

import com.miu.waafinalproject.model.ResponseModel;
import com.miu.waafinalproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
    private ResponseModel responseModel;
    private final UserService userService;

    @GetMapping("/userDetails")
    public ResponseEntity<ResponseModel> getUserDetails() {
        responseModel = userService.getLoggedInUserDetails();
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }
}
