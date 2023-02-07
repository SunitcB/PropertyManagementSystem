package com.miu.waafinalproject.service.impl;

import com.miu.waafinalproject.model.ResponseModel;
import com.miu.waafinalproject.model.responseDTO.AddressResponseModel;
import com.miu.waafinalproject.model.responseDTO.PropertyListResponseModel;
import com.miu.waafinalproject.repository.PropertyRepo;
import com.miu.waafinalproject.service.OwnerService;
import com.miu.waafinalproject.service.UserService;
import com.miu.waafinalproject.utils.PropertyImageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {
    private ResponseModel responseModel;
    private final PropertyImageUtil imageUtil;
    private final PropertyRepo propertyRepo;
    private final UserService userService;

    @Override
    public ResponseModel getAllOwnedPropertyList() {
        responseModel = new ResponseModel();
        responseModel.setStatus(HttpStatus.OK);
        List<PropertyListResponseModel> propertyList = new ArrayList<>();
        propertyRepo.findAllByOwner(userService.getLoggedInUser()).forEach(p ->
                {
                    try {
                        propertyList.add(new PropertyListResponseModel(
                                p.getId(),
                                p.getTitle(),
                                p.getPropertyDetail().getDescription(),
                                p.getPrice(),
                                new AddressResponseModel(p.getAddress()).toString(),
                                imageUtil.imageToBase64(),
                                p.getPropertyOption().getType(),
                                p.getPropertyDetail().getBed(),
                                p.getPropertyDetail().getBath(),
                                p.getBuiltYear()
                        ));
                    } catch (IOException e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
                }
        );
        responseModel.setData(propertyList);
        return responseModel;
    }
}
