package com.miu.waafinalproject.controller;

import com.miu.waafinalproject.model.ResponseModel;
import com.miu.waafinalproject.model.requestDTO.PropertyRequestModel;
import com.miu.waafinalproject.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping("/api/property")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PropertyController {
    private ResponseModel responseModel;
    private final PropertyService propertyService;

    @GetMapping
    public ResponseEntity<ResponseModel> getAllProperty(@RequestParam(required = false) String filter) {
        HashMap<String, Object> filterMap = new HashMap<>();
        filterMap.put("filter", filter);
        responseModel = propertyService.getAll(filterMap);
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }

    @GetMapping("/{id}")
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
        responseModel = propertyService.update(requestModel);
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseModel> deleteProperty(@PathVariable UUID id) {
        responseModel = propertyService.delete(id);
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }
}
