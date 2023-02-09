package com.miu.waafinalproject.model.responseDTO;

import com.miu.waafinalproject.service.charts.FavoriteCountChartData;
import com.miu.waafinalproject.service.charts.OwnerPropertyTypeCountChartData;
import com.miu.waafinalproject.service.charts.PropertyApplicationCountChartData;
import com.miu.waafinalproject.service.charts.PropertyViewCountChartData;
import lombok.Data;

import java.util.List;

@Data
public class OwnerDashboardResponseModel {
    List<PropertyApplicationCountChartData> propertyApplicationCountChartData;
    List<PropertyViewCountChartData> propertyViewCountChartData;
    List<OwnerPropertyTypeCountChartData> ownerPropertyTypeCountChartData;
    List<FavoriteCountChartData> favoriteCountChartData;
}
