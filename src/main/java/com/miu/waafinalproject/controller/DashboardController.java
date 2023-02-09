package com.miu.waafinalproject.controller;

import com.miu.waafinalproject.model.ResponseModel;
import com.miu.waafinalproject.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DashboardController {
    private ResponseModel responseModel;
    private final DashboardService dashboardService;
    @GetMapping
    public ResponseEntity<ResponseModel> getData(){
        responseModel = dashboardService.getDashboardChartData();
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }
}
