package com.miu.waafinalproject.service.impl;

import com.miu.waafinalproject.model.ResponseModel;
import com.miu.waafinalproject.model.responseDTO.AddressResponseModel;
import com.miu.waafinalproject.model.responseDTO.OwnerPropertyListResponseModel;
import com.miu.waafinalproject.model.responseDTO.PropertyApplicationResponseModel;
import com.miu.waafinalproject.repository.PropertyApplicationRepo;
import com.miu.waafinalproject.repository.PropertyRepo;
import com.miu.waafinalproject.service.OwnerService;
import com.miu.waafinalproject.service.UserService;
import com.miu.waafinalproject.utils.PropertyImageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {
    private ResponseModel responseModel;
    private final PropertyImageUtil imageUtil;
    private final PropertyRepo propertyRepo;
    private final UserService userService;
    private final PropertyApplicationRepo applicationRepo;

    @Override
    public ResponseModel getAllOwnedPropertyList() {
        responseModel = new ResponseModel();
        responseModel.setStatus(HttpStatus.OK);
        List<OwnerPropertyListResponseModel> propertyList = new ArrayList<>();
        propertyRepo.findAllByOwner(userService.getLoggedInUser()).forEach(p ->
                {
                    propertyList.add(new OwnerPropertyListResponseModel(
                            p.getId(),
                            p.getTitle(),
                            p.getPropertyDetail().getDescription(),
                            p.getPrice(),
                            new AddressResponseModel(p.getAddress()).toString(),
                            p.getPropertyOption().getType(),
                            p.getPropertyDetail().getBed(),
                            p.getPropertyDetail().getBath(),
                            p.getBuiltYear(),
                            p.getApplications().size(),
                            p.getPropertyView().size()
                    ));
                }
        );
        responseModel.setData(propertyList);
        return responseModel;
    }

    @Override
    public ResponseModel getAllOwnedPropertyApplicationList() {
        responseModel = new ResponseModel();
        responseModel.setStatus(HttpStatus.OK);
        List<PropertyApplicationResponseModel> propertyList = new ArrayList<>();
        applicationRepo.findAllByProperty_Owner(userService.getLoggedInUser()).forEach(p ->
                {
                    propertyList.add(new PropertyApplicationResponseModel(
                            p.getId(),
                            p.getStatus(),
                            p.getRemarks(),
                            p.getOfferPrice(),
                            p.getUsers().getUserFullName(),
                            p.getProperty().getTitle(),
                            p.getProperty().getOwner().getUserFullName()
                    ));
                }
        );
        responseModel.setData(propertyList);
        return responseModel;
    }
}
