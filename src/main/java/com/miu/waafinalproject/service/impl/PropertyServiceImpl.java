package com.miu.waafinalproject.service.impl;

import com.miu.waafinalproject.domain.Address;
import com.miu.waafinalproject.domain.Property;
import com.miu.waafinalproject.domain.PropertyDetail;
import com.miu.waafinalproject.model.ResponseModel;
import com.miu.waafinalproject.model.requestDTO.PropertyRequestModel;
import com.miu.waafinalproject.model.responseDTO.AddressResponseModel;
import com.miu.waafinalproject.model.responseDTO.PropertyListResponseModel;
import com.miu.waafinalproject.model.responseDTO.PropertyResponseModel;
import com.miu.waafinalproject.repository.*;
import com.miu.waafinalproject.service.PropertyService;
import com.miu.waafinalproject.service.UserService;
import com.miu.waafinalproject.utils.PropertyImageUtil;
import com.miu.waafinalproject.utils.enums.PropertyStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {
    private final PropertyRepo propertyRepo;
    private final PropertyDetailRepo propertyDetailRepo;
    private final AddressRepo addressRepo;
    private final PropertyOptionRepo propertyOptionRepo;
    private final PropertyTypeRepo propertyTypeRepo;
    private ResponseModel responseModel;
    private final UsersRepo usersRepo;
    private final UserService userService;

    private final PropertyImageUtil imageUtil;

    @Override
    public ResponseModel getAll(Object filters) {
        responseModel = new ResponseModel();
        responseModel.setStatus(HttpStatus.OK);
        List<PropertyListResponseModel> responseObj = new ArrayList<>();

        propertyRepo.findAll(Sort.by("title")).forEach(x -> {

            try {
                responseObj.add(
                        new PropertyListResponseModel(
                                x.getId(),
                                x.getTitle(),
                                x.getPropertyDetail().getDescription(),
                                x.getPrice(),
                                new AddressResponseModel(x.getAddress()).toString(),
                                imageUtil.imageToBase64(),
                                x.getPropertyOption().getType(),
                                x.getPropertyDetail().getBed(),
                                x.getPropertyDetail().getBath(),
                                x.getBuiltYear()
                        ));
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        });

        responseModel.setData(responseObj);
        return responseModel;
    }

    @Override
    public ResponseModel getById(UUID id) {
        System.out.println("DASAD");
        System.out.println(id);
        responseModel = new ResponseModel();
        responseModel.setStatus(HttpStatus.OK);
        Property property = propertyRepo.findById(id).get();
        try {
            responseModel.setData(new PropertyResponseModel(
                    property.getId(),
                    property.getTitle(),
                    property.getPropertyDetail(),
                    (property.getPropertyOption() != null) ? property.getPropertyOption().getType() : null,
                    property.getPrice(),
                    (property.getPropertyType() != null) ? property.getPropertyType().getName() : null,
                    property.getAddress(),
                    (property.getPropertyView() != null) ? property.getPropertyView().getCount() : 0,
                    imageUtil.imageToBase64(),
                    property.getBuiltYear(),
                    property.getPropertyStatus()));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return responseModel;
    }

    @Override
    public ResponseModel save(PropertyRequestModel requestModel) {
        responseModel = new ResponseModel();

        Property property = new Property();
        property.setTitle(requestModel.getTitle());
        PropertyDetail propertyDetail = new PropertyDetail(null, requestModel.getDescription(), requestModel.getBed(), requestModel.getBath(), requestModel.getHasBasement(), requestModel.getHasParking(), requestModel.getArea(), requestModel.getFeatures());
        property.setPropertyDetail(propertyDetailRepo.save(propertyDetail));
        Address address = new Address(null, requestModel.getStreet(), requestModel.getCity(), requestModel.getState(), requestModel.getZipcode());
        property.setAddress(addressRepo.save(address));
        property.setPrice(requestModel.getPrice());
        property.setBuiltYear(requestModel.getBuiltYear());
        property.setPropertyOption(propertyOptionRepo.findByType(requestModel.getPropertyOption()));
        property.setPropertyType(propertyTypeRepo.findByName(requestModel.getPropertyType()));
        property.setOwner(userService.getLoggedInUser());
        property.setPropertyStatus(requestModel.getPropertyStatus());
        propertyRepo.save(property);
        responseModel.setStatus(HttpStatus.OK);
        responseModel.setMessage("Property has been saved successfully.");
        return responseModel;
    }

    @Override
    public ResponseModel update(UUID id, PropertyRequestModel requestModel) {
        responseModel = new ResponseModel();
        responseModel.setStatus(HttpStatus.OK);
        Property property = new Property();

        property.setId(id);
        property.setTitle(requestModel.getTitle());
        PropertyDetail propertyDetail = new PropertyDetail(null, requestModel.getDescription(), requestModel.getBed(), requestModel.getBath(), requestModel.getHasBasement(), requestModel.getHasParking(), requestModel.getArea(), requestModel.getFeatures());
        property.setPropertyDetail(propertyDetailRepo.save(propertyDetail));
        Address address = new Address(null, requestModel.getStreet(), requestModel.getCity(), requestModel.getState(), requestModel.getZipcode());
        property.setAddress(addressRepo.save(address));
        property.setPrice(requestModel.getPrice());
        property.setBuiltYear(requestModel.getBuiltYear());
        property.setPropertyOption(propertyOptionRepo.findByType(requestModel.getPropertyOption()));
        property.setPropertyType(propertyTypeRepo.findByName(requestModel.getPropertyType()));
        property.setPropertyStatus(requestModel.getPropertyStatus());
        propertyRepo.save(property);
        responseModel.setMessage("Property has been updated successfully.");
        return responseModel;
    }

    @Override
    public ResponseModel delete(UUID id) {
        responseModel = new ResponseModel();
        Property propertyObj = propertyRepo.findById(id).get();
        if (propertyObj.getPropertyStatus().equals(PropertyStatus.PENDING)) {
            responseModel.setStatus(HttpStatus.NOT_ACCEPTABLE);
            responseModel.setMessage("Property has pending status so cannot be deleted.");
        } else {
            responseModel.setStatus(HttpStatus.OK);
            propertyRepo.deleteById(id);
            responseModel.setMessage("Property has been deleted successfully.");
        }
        return responseModel;
    }
}
