package com.miu.waafinalproject.service.impl;

import com.miu.waafinalproject.model.ResponseModel;
import com.miu.waafinalproject.model.responseDTO.AdminDashboardResponseModel;
import com.miu.waafinalproject.model.responseDTO.OwnerDashboardResponseModel;
import com.miu.waafinalproject.repository.*;
import com.miu.waafinalproject.service.DashboardService;
import com.miu.waafinalproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {
    private ResponseModel responseModel;
    private final PropertyViewRepo propertyViewRepo;
    private final PropertyApplicationRepo applicationRepo;
    private final AddressRepo addressRepo;
    private final PropertyTypeRepo propertyTypeRepo;
    private final UserService userService;
    private final FavoriteRepo favoriteRepo;

    @Override
    public ResponseModel getAdminDashboardChartData() {
        responseModel = new ResponseModel();

        AdminDashboardResponseModel dashboardModel = new AdminDashboardResponseModel();
        dashboardModel.setStatePropertyChartData(addressRepo.findAllStateCount());
        dashboardModel.setPropertyTypeCountChartData(propertyTypeRepo.getPropertyTypeCountChartData());

        responseModel.setData(dashboardModel);
        responseModel.setStatus(HttpStatus.OK);

        return responseModel;
    }

    @Override
    public ResponseModel getOwnerDashboardChartData() {
        responseModel = new ResponseModel();

        Long userId = userService.getLoggedInUser().getId();
        OwnerDashboardResponseModel dashboardModel = new OwnerDashboardResponseModel();
        dashboardModel.setPropertyApplicationCountChartData(applicationRepo.getOwnedPropertyCount(userId));
        dashboardModel.setPropertyViewCountChartData(propertyViewRepo.getPropertyViewCount(userId));
        dashboardModel.setOwnerPropertyTypeCountChartData(propertyTypeRepo.getOwnerPropertyTypeCountChartData(userId));
        dashboardModel.setFavoriteCountChartData(favoriteRepo.getFavouriteChartData(userId));
        responseModel.setData(dashboardModel);
        responseModel.setStatus(HttpStatus.OK);

        return responseModel;
    }
}
