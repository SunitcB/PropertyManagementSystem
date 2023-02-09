package com.miu.waafinalproject.controller;

import com.miu.waafinalproject.model.ResponseModel;
import com.miu.waafinalproject.model.requestDTO.LoginRequestModel;
import com.miu.waafinalproject.model.requestDTO.UserRequestModel;
import com.miu.waafinalproject.service.AuthenticationService;
import com.miu.waafinalproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginController {
    private ResponseModel responseModel;
    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<ResponseModel> loginUser(@RequestBody LoginRequestModel loginRequestModel) {
        responseModel = authenticationService.authenticateUser(loginRequestModel);
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }

    @PostMapping("/signup")
    public ResponseEntity<ResponseModel> saveUser(@RequestBody UserRequestModel requestModel) {
        responseModel = userService.save(requestModel);
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }

    @PatchMapping("/forgotPassword")
    public ResponseEntity<ResponseModel> forgotPassword(@RequestParam String userName) {
        responseModel = authenticationService.forgotPassword(userName);
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }

    @PutMapping("/resetPassword/{token}")
    public ResponseEntity<ResponseModel> resetPassword(@RequestBody LoginRequestModel forgotModel, @PathVariable String token) {
        responseModel = authenticationService.resetPassword(forgotModel, token);
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }
}
