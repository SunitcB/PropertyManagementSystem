package com.miu.waafinalproject.service;

import com.miu.waafinalproject.model.ResponseModel;


public interface DashboardService {

    ResponseModel getAdminDashboardChartData();
    ResponseModel getOwnerDashboardChartData();
}
