package com.miu.waafinalproject.controller;

import com.miu.waafinalproject.model.ResponseModel;
import com.miu.waafinalproject.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DashboardController {
    private ResponseModel responseModel;
    private final DashboardService dashboardService;

    @GetMapping("/admin")
    public ResponseEntity<ResponseModel> getAdminData() {
        responseModel = dashboardService.getAdminDashboardChartData();
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }

    @GetMapping("/owner")
    public ResponseEntity<ResponseModel> getOwnerData() {
        responseModel = dashboardService.getOwnerDashboardChartData();
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }
}
