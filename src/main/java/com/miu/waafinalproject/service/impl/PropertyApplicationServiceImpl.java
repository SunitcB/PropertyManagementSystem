package com.miu.waafinalproject.service.impl;

import com.miu.waafinalproject.domain.Property;
import com.miu.waafinalproject.domain.PropertyApplication;
import com.miu.waafinalproject.model.ResponseModel;
import com.miu.waafinalproject.model.requestDTO.PropertyApplicationRequestModel;
import com.miu.waafinalproject.model.responseDTO.PdfResponseModel;
import com.miu.waafinalproject.model.responseDTO.PropertyApplicationResponseModel;
import com.miu.waafinalproject.repository.PropertyApplicationRepo;
import com.miu.waafinalproject.repository.PropertyRepo;
import com.miu.waafinalproject.service.PropertyApplicationService;
import com.miu.waafinalproject.service.UserService;
import com.miu.waafinalproject.utils.EmailSenderUtil;
import com.miu.waafinalproject.utils.enums.PropertyApplicationStatus;
import com.miu.waafinalproject.utils.enums.PropertyStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
    private final EmailSenderUtil emailSenderUtil;

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
    public PdfResponseModel getPdfResponseModel(long id) {
        PdfResponseModel pdfModel = new PdfResponseModel();
        PropertyApplication application = applicationRepo.findById(id).get();
        pdfModel.setTax(7);
        pdfModel.setPropertyDetail(application.getProperty().getPropertyDetail());
        pdfModel.setCostPrice(application.getOfferPrice());
        pdfModel.setContractDate(LocalDate.now());
        pdfModel.setAddress(application.getProperty().getAddress());
        pdfModel.setSellingPrice((pdfModel.getTax() / 100 * pdfModel.getCostPrice()) + pdfModel.getCostPrice());
        pdfModel.setBuyerName(application.getProperty().getOwner().getUserFullName());
        pdfModel.setSellerName(application.getUsers().getUserFullName());
        return pdfModel;
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
            emailSenderUtil.sendSimpleEmail(application.getProperty().getOwner().getEmail(), "Your property has a new offer", "Dear " + application.getProperty().getOwner().getFirstName() + ",\n" +
                    "Your property listed on SRNA portal has a new offer. PLease login to the portal to view the details." +
                    "\n" +
                    "Yours truly,\n" +
                    "The SRNA team");
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
    public ResponseModel acceptOffer(Long id, String action) {
        responseModel = new ResponseModel();
        PropertyApplication applicationObj = applicationRepo.findById(id).get();
        Property targetProperty = applicationObj.getProperty();
        applicationObj.setStatus(action);
        applicationRepo.save(applicationObj);

        if (action.equals(PropertyApplicationStatus.APPROVED.toString())) {
            responseModel.setMessage("Property offer has been accepted.");
            targetProperty.setPropertyStatus(PropertyStatus.PENDING.toString());
            propertyRepo.save(targetProperty);

            for (PropertyApplication restOfApplication : applicationRepo.findAllByProperty_Id(targetProperty.getId())) {
                if (restOfApplication.getId() != id) {
                    restOfApplication.setStatus(PropertyApplicationStatus.REJECTED.toString());
                    applicationRepo.save(restOfApplication);
                }
            }
            emailSenderUtil.sendSimpleEmail(applicationObj.getUsers().getEmail(), "Your property offer has been accepted by seller", "Dear " + applicationObj.getUsers().getFirstName() + ",\n\n" +
                    "Your property offer has been approved by the seller. PLease login to the portal to view the details." +
                    "\n\n" +
                    "Yours truly,\n" +
                    "The SRNA team");
        } else {
            responseModel.setMessage("Property offer has been rejected.");
            targetProperty.setPropertyStatus(PropertyStatus.AVAILABLE.toString());
            propertyRepo.save(targetProperty);
        }

        responseModel.setStatus(HttpStatus.OK);
        return responseModel;
    }
}
