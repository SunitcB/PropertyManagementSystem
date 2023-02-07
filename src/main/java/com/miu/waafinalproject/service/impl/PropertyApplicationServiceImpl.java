package com.miu.waafinalproject.service.impl;

import com.miu.waafinalproject.domain.PropertyApplication;
import com.miu.waafinalproject.model.ResponseModel;
import com.miu.waafinalproject.model.requestDTO.PropertyApplicationRequestModel;
import com.miu.waafinalproject.model.responseDTO.PropertyApplicationResponseModel;
import com.miu.waafinalproject.repository.PropertyApplicationRepo;
import com.miu.waafinalproject.repository.PropertyRepo;
import com.miu.waafinalproject.service.PropertyApplicationService;
import com.miu.waafinalproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PropertyApplicationServiceImpl implements PropertyApplicationService {
    private ResponseModel responseModel;
    private final PropertyApplicationRepo applicationRepo;
    private final PropertyRepo propertyRepo;
    private final UserService userService;

    @Override
    public ResponseModel getAllOffersToProperty(UUID propertyId) {
        List<PropertyApplicationResponseModel> responseObj = new ArrayList<>();
        List<PropertyApplication> applicationList = new ArrayList<>();
        if (propertyId != null) {
            applicationList = applicationRepo.findAllByProperty_Id(propertyId);
        } else {
            applicationRepo.findAll().forEach(applicationList::add);
        }
        applicationList.forEach(application ->
                responseObj.add(new PropertyApplicationResponseModel(
                                application.getId(),
                                application.getStatus(),
                                application.getRemarks(),
                                application.getOfferPrice(),
                                application.getUsers().getUserFullName()
                        )
                )
        );

        responseModel = new ResponseModel();
        responseModel.setStatus(HttpStatus.OK);
        responseModel.setData(responseObj);
        return responseModel;
    }

    @Override
    public ResponseModel saveOffer(PropertyApplicationRequestModel applicationModel) {
        responseModel = new ResponseModel();
        responseModel.setStatus(HttpStatus.OK);
        PropertyApplication application = new PropertyApplication();
        application.setProperty(propertyRepo.findById(applicationModel.getPropertyId()).get());
        application.setStatus(applicationModel.getStatus());
        application.setRemarks(applicationModel.getRemarks());
        application.setOfferPrice(applicationModel.getOfferPrice());
        application.setUsers(userService.getLoggedInUser());
        applicationRepo.save(application);
        responseModel.setMessage("Property offer application has been submitted.");
        return responseModel;
    }

    @Override
    public ResponseModel deleteOffer(Long applicationId) {
        responseModel = new ResponseModel();
        responseModel.setStatus(HttpStatus.OK);
        applicationRepo.deleteById(applicationId);
        responseModel.setMessage("Property offer application has been removed.");
        return responseModel;
    }

    @Override
    public ResponseModel updateOffer(Long id, PropertyApplicationRequestModel applicationModel) {
        responseModel = new ResponseModel();
        responseModel.setStatus(HttpStatus.OK);
        PropertyApplication application = new PropertyApplication();
        application.setId(id);
        application.setProperty(propertyRepo.findById(applicationModel.getPropertyId()).get());
        application.setStatus(applicationModel.getStatus());
        application.setRemarks(applicationModel.getRemarks());
        application.setOfferPrice(applicationModel.getOfferPrice());
        application.setUsers(userService.getLoggedInUser());
        applicationRepo.save(application);
        responseModel.setMessage("Property offer application has been submitted.");
        return responseModel;
    }
}
