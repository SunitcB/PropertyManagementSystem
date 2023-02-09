package com.miu.waafinalproject.service.impl;

import com.miu.waafinalproject.model.ResponseModel;
import com.miu.waafinalproject.model.responseDTO.AdminDashboardResponseModel;
import com.miu.waafinalproject.repository.AddressRepo;
import com.miu.waafinalproject.repository.PropertyRepo;
import com.miu.waafinalproject.repository.PropertyTypeRepo;
import com.miu.waafinalproject.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {
    private ResponseModel responseModel;
    private final PropertyRepo propertyRepo;
    private final AddressRepo addressRepo;
    private final PropertyTypeRepo propertyTypeRepo;

    @Override
    public ResponseModel getDashboardChartData() {
        responseModel = new ResponseModel();

        AdminDashboardResponseModel dashboardModel = new AdminDashboardResponseModel();
        dashboardModel.setStatePropertyChartData(addressRepo.findAllStateCount());
        dashboardModel.setPropertyTypeCountChartData(propertyTypeRepo.getPropertyTypeCountChartData());

        responseModel.setData(dashboardModel);
        responseModel.setStatus(HttpStatus.OK);

        return responseModel;
    }
}
