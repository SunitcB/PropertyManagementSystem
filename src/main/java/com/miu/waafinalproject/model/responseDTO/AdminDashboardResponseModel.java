package com.miu.waafinalproject.model.responseDTO;

import com.miu.waafinalproject.service.charts.PropertyTypeCountChartData;
import com.miu.waafinalproject.service.charts.StatePropertyChartData;
import lombok.Data;

import java.util.List;

@Data
public class AdminDashboardResponseModel {
    List<StatePropertyChartData> statePropertyChartData;
    List<PropertyTypeCountChartData> propertyTypeCountChartData;
}
