package com.miu.waafinalproject.service;

import com.miu.waafinalproject.model.ResponseModel;
import com.miu.waafinalproject.model.requestDTO.PropertyApplicationRequestModel;
import com.miu.waafinalproject.model.responseDTO.PdfResponseModel;

import java.util.UUID;

public interface PropertyApplicationService {
    ResponseModel getAllOffersToProperty(UUID propertyId);
    ResponseModel saveOffer(PropertyApplicationRequestModel applicationModel);
    ResponseModel deleteOffer(Long applicationId);
    ResponseModel updateOffer(Long id, PropertyApplicationRequestModel applicationModel);
    ResponseModel acceptOffer(Long id, String action);
    ResponseModel getAllOfMyOffers();
    PdfResponseModel getPdfResponseModel(long id);
}
