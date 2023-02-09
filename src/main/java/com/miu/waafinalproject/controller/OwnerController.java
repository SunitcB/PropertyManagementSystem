package com.miu.waafinalproject.controller;

import com.miu.waafinalproject.model.ResponseModel;
import com.miu.waafinalproject.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

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
    public ResponseEntity<ResponseModel> getOwnersPropertyApplicationList(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String submissionDate,
            @RequestParam(required = false) String location
    ) {
        HashMap<String, Object> filters = new HashMap<>();
        if(title == null && submissionDate == null && location == null){
            responseModel = ownerService.getAllOwnedPropertyApplicationList(null);
        }else {
            filters.put("title", title);
            filters.put("submissionDate", submissionDate);
            filters.put("location", location);
            responseModel = ownerService.getAllOwnedPropertyApplicationList(filters);
        }


        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }
}
