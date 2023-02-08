package com.miu.waafinalproject.controller;

import com.miu.waafinalproject.model.ResponseModel;
import com.miu.waafinalproject.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/owner")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OwnerController {
    private ResponseModel responseModel;
    private final OwnerService ownerService;

    @GetMapping("/property")
    public ResponseEntity<ResponseModel> getOwnersPropertyList() {
        responseModel = ownerService.getAllOwnedPropertyList();
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }


    @GetMapping("/application")
    public ResponseEntity<ResponseModel> getOwnersPropertyApplicationList() {
        responseModel = ownerService.getAllOwnedPropertyApplicationList();
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }
}
