package com.miu.waafinalproject.service.impl;

import com.miu.waafinalproject.domain.Property;
import com.miu.waafinalproject.domain.PropertyApplication;
import com.miu.waafinalproject.model.ResponseModel;
import com.miu.waafinalproject.model.requestDTO.PropertyApplicationRequestModel;
import com.miu.waafinalproject.model.responseDTO.PropertyApplicationResponseModel;
import com.miu.waafinalproject.repository.PropertyApplicationRepo;
import com.miu.waafinalproject.repository.PropertyRepo;
import com.miu.waafinalproject.service.PropertyApplicationService;
import com.miu.waafinalproject.service.UserService;
import com.miu.waafinalproject.utils.enums.ApplicationStatus;
import com.miu.waafinalproject.utils.enums.PropertyApplicationStatus;
import com.miu.waafinalproject.utils.enums.PropertyStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
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
                                application.getUsers().getUserFullName(),
                                application.getProperty().getTitle(),
                                application.getProperty().getOwner().getUserFullName()
                        )
                )
        );

        responseModel = new ResponseModel();
        responseModel.setStatus(HttpStatus.OK);
        responseModel.setData(responseObj);
        return responseModel;
    }

    @Override
    public ResponseModel getAllOfMyOffers() {
        responseModel = new ResponseModel();
        List<PropertyApplicationResponseModel> responseObj = new ArrayList<>();
        applicationRepo.findAllByUsers_Id(userService.getLoggedInUser().getId()).forEach(app ->
                responseObj.add(new PropertyApplicationResponseModel(
                        app.getId(),
                        app.getStatus(),
                        app.getRemarks(),
                        app.getOfferPrice(),
                        app.getUsers().getUserFullName(),
                        app.getProperty().getTitle(),
                        app.getProperty().getOwner().getUserFullName()
                ))
        );
        responseModel.setData(responseObj);
        responseModel.setStatus(HttpStatus.OK);
        return responseModel;
    }

    @Override
    public ResponseModel saveOffer(PropertyApplicationRequestModel applicationModel) {
        responseModel = new ResponseModel();
        Property propertyObj = propertyRepo.findById(applicationModel.getPropertyId()).get();
        if (propertyObj == null
                || (propertyObj.getPropertyStatus().equals(PropertyStatus.CONTINGENT.toString())
                || propertyObj.getPropertyStatus().equals(PropertyStatus.PENDING.toString()))) {
            responseModel.setStatus(HttpStatus.NOT_ACCEPTABLE);
            responseModel.setMessage("Property offer is not allowed for the property.");
        } else {
            responseModel.setStatus(HttpStatus.OK);
            PropertyApplication application = new PropertyApplication();
            application.setProperty(propertyObj);
            application.setStatus(PropertyApplicationStatus.PENDING.toString());
            application.setRemarks(applicationModel.getRemarks());
            application.setOfferPrice(applicationModel.getOfferPrice());
            application.setUsers(userService.getLoggedInUser());
            applicationRepo.save(application);
            responseModel.setMessage("Property offer application has been submitted.");
        }

        return responseModel;
    }

    @Override
    public ResponseModel deleteOffer(Long applicationId) {
        responseModel = new ResponseModel();
        PropertyApplication propertyApplicationObj = applicationRepo.findById(applicationId).get();
        if (propertyApplicationObj.getStatus().equals(PropertyApplicationStatus.PENDING.toString())) {
            responseModel.setStatus(HttpStatus.OK);
            applicationRepo.deleteById(applicationId);
            responseModel.setMessage("Application has been removed.");
        } else {
            responseModel.setStatus(HttpStatus.NOT_ACCEPTABLE);
            responseModel.setMessage("Application has accepted/rejected status so cannot be deleted.");
        }
        return responseModel;
    }

    @Override
    public ResponseModel updateOffer(Long id, PropertyApplicationRequestModel applicationModel) {
        responseModel = new ResponseModel();
        responseModel.setStatus(HttpStatus.OK);
        Property targetProperty = propertyRepo.findById(applicationModel.getPropertyId()).get();
        PropertyApplication application = new PropertyApplication();
        application.setId(id);
        application.setProperty(targetProperty);
        application.setStatus(applicationModel.getStatus());
        application.setRemarks(applicationModel.getRemarks());
        application.setOfferPrice(applicationModel.getOfferPrice());
        application.setUsers(userService.getLoggedInUser());
        applicationRepo.save(application);

        responseModel.setMessage("Property offer application has been submitted.");
        return responseModel;
    }

    @Override
    public ResponseModel acceptOffer(Long id) {
        responseModel = new ResponseModel();
        PropertyApplication applicationObj = applicationRepo.findById(id).get();
        Property targetProperty = applicationObj.getProperty();

        applicationObj.setStatus(ApplicationStatus.ACCEPT.toString());

        targetProperty.setPropertyStatus(PropertyStatus.PENDING.toString());
        propertyRepo.save(targetProperty);

        for (PropertyApplication restOfApplication : applicationRepo.findAllByProperty_Id(targetProperty.getId())) {
            if(restOfApplication.getId()!=id){
                restOfApplication.setStatus(ApplicationStatus.REJECT.toString());
                applicationRepo.save(restOfApplication);
            }
        }
        responseModel.setStatus(HttpStatus.OK);
        responseModel.setMessage("Property offer has been accepted.");
        return responseModel;
    }
}
