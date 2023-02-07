package com.miu.waafinalproject.controller;

import com.miu.waafinalproject.model.ResponseModel;
import com.miu.waafinalproject.model.requestDTO.LoginRequestModel;
import com.miu.waafinalproject.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class LoginController {
    private ResponseModel responseModel;
    private final AuthenticationService authenticationService;
    @PostMapping
    public ResponseEntity<ResponseModel> loginUser(@RequestBody LoginRequestModel loginRequestModel){
        responseModel = authenticationService.authenticateUser(loginRequestModel);
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }
}
