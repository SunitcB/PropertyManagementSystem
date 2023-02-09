package com.miu.waafinalproject.controller;

import com.miu.waafinalproject.model.ResponseModel;
import com.miu.waafinalproject.model.requestDTO.PropertyApplicationRequestModel;
import com.miu.waafinalproject.service.PropertyApplicationService;
import com.miu.waafinalproject.utils.PdfExporterUtils;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseModel> acceptOffer(@PathVariable Long id, @RequestParam String action) {
        responseModel = propertyApplicationService.acceptOffer(id, action);
        return ResponseEntity.status(responseModel.getStatus()).body(responseModel);
    }

    @GetMapping("/{id}/download")
    public void downloadPdf(@PathVariable long id, HttpServletResponse response){
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);


        PdfExporterUtils exporter = new PdfExporterUtils(propertyApplicationService.getPdfResponseModel(id));
        try {
            exporter.export(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
