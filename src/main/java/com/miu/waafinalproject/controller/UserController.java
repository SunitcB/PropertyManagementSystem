package com.miu.waafinalproject.controller;

import com.miu.waafinalproject.model.ResponseModel;
import com.miu.waafinalproject.model.requestDTO.UserRequestModel;
import com.miu.waafinalproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<ResponseModel> getAllUser(@RequestParam(required = false) String filter) {
        //responseModel = userService;
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseModel> getUserById(@PathVariable Long id) {
        responseModel = userService.getById(id);
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseModel> updateUser(@PathVariable Long id, @RequestBody UserRequestModel requestModel){
       responseModel = userService.update(id, requestModel);
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseModel> deleteUser(@PathVariable Long id){
        responseModel = userService.delete(id);
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }
}
