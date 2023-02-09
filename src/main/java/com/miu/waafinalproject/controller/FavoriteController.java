package com.miu.waafinalproject.controller;

import com.miu.waafinalproject.model.ResponseModel;
import com.miu.waafinalproject.service.FavoriteService;
import com.miu.waafinalproject.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
@RestController
@RequestMapping("/api/favorite")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FavoriteController {
    private ResponseModel responseModel;
    private final FavoriteService favoriteService;
    @PostMapping("/property/{id}")
    public ResponseEntity<ResponseModel> save(@PathVariable UUID id){
        responseModel = favoriteService.favorite(id);
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }

    @GetMapping
    public  ResponseEntity<ResponseModel> getAll(){
        responseModel = favoriteService.getAll();
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }
}
