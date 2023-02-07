package com.miu.waafinalproject.controller;

import com.miu.waafinalproject.model.ResponseModel;
import com.miu.waafinalproject.model.requestDTO.PropertyApplicationRequestModel;
import com.miu.waafinalproject.service.PropertyApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/application")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ApplicationController {
    ResponseModel responseModel;
    private final PropertyApplicationService propertyApplicationService;

    @GetMapping
    public ResponseEntity<ResponseModel> getAllByProperty(@RequestParam(required = false) UUID propertyId) {
        responseModel = propertyApplicationService.getAllOffersToProperty(propertyId);
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }

    @GetMapping("/myApplication")
    public ResponseEntity<ResponseModel> getAllMyApplications() {
        responseModel = propertyApplicationService.getAllOfMyOffers();
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseModel> getPropertyApplication(@PathVariable Long id) {
//        responseModel = propertyApplicationService.getAllOffersToProperty(id);
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }

    @PostMapping
    public ResponseEntity<ResponseModel> sendOffer(@RequestBody PropertyApplicationRequestModel applicationModel) {
        responseModel = propertyApplicationService.saveOffer(applicationModel);
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseModel> updateOffer(@PathVariable Long id, @RequestBody PropertyApplicationRequestModel applicationModel) {
        responseModel = propertyApplicationService.updateOffer(id, applicationModel);
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseModel> deleteOffer(@PathVariable Long id) {
        responseModel = propertyApplicationService.deleteOffer(id);
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }

}
