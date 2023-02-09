package com.miu.waafinalproject.service.charts;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OwnerPropertyTypeCountChartData {
    String propertyType;
    Long count;
}
