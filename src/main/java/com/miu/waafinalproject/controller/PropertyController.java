package com.miu.waafinalproject.controller;

import com.miu.waafinalproject.aop.aspect.HandleView;
import com.miu.waafinalproject.model.ResponseModel;
import com.miu.waafinalproject.model.requestDTO.PropertyRequestModel;
import com.miu.waafinalproject.service.FileStorageService;
import com.miu.waafinalproject.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping("/api/property")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PropertyController {
    private ResponseModel responseModel;
    private final PropertyService propertyService;
    private final FileStorageService storageService;

    @GetMapping
    public ResponseEntity<ResponseModel> getAllProperty(
            @RequestParam(required = false) Double price,
            @RequestParam(required = false) String propertyOption,
            @RequestParam(required = false) Integer roomSize,
            @RequestParam(required = false) String submissionDate,
            @RequestParam(required = false) String propertyType,
            @RequestParam(required = false) String location) {
        HashMap<String, Object> filterMap = new HashMap<>();
        filterMap.put("price", price);
        filterMap.put("propertyOption", propertyOption);
        filterMap.put("roomSize", roomSize);
        filterMap.put("propertyType", propertyType);
        filterMap.put("location", location);
        if (price == null && propertyOption == null && roomSize == null && propertyType == null && location == null) {
            responseModel = propertyService.getAll(null);
        } else {
            responseModel = propertyService.getAll(filterMap);
        }
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }

    @GetMapping("/{id}")
    @HandleView
    public ResponseEntity<ResponseModel> getPropertyById(@PathVariable UUID id) {
        responseModel = propertyService.getById(id);
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }

    @PostMapping
    public ResponseEntity<ResponseModel> saveProperty(@RequestBody PropertyRequestModel requestModel) {
        responseModel = propertyService.save(requestModel);
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseModel> updateProperty(@PathVariable UUID id, @RequestBody PropertyRequestModel requestModel) {
        responseModel = propertyService.update(id, requestModel);
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseModel> showHideProperty(@PathVariable UUID id, @RequestParam String action) {
        responseModel = propertyService.showHideProperty(id, action);
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseModel> deleteProperty(@PathVariable UUID id) {
        responseModel = propertyService.delete(id);
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }

    @PatchMapping("/{id}/contingent")
    public ResponseEntity<ResponseModel> makeContingent(@PathVariable UUID id) {
        responseModel = propertyService.makeContingent(id);
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }

    @PatchMapping("{id}/cancelContingent")
    public ResponseEntity<ResponseModel> cancelContingent(@PathVariable UUID id){
        responseModel = propertyService.cancelContingent(id);
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }

    @PostMapping("/propertyFile")
    public ResponseEntity<ResponseModel> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam String propertyId) {
        responseModel = storageService.save(file, propertyId);
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }

    @DeleteMapping("/propertyFile/{id}")
    public ResponseEntity<ResponseModel> deleteFile(@PathVariable UUID id) {
        try {
            responseModel = storageService.deleteFile(id.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }
}
