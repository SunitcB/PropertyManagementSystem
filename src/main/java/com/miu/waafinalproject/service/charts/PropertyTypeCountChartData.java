package com.miu.waafinalproject.service.charts;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PropertyTypeCountChartData {
    String propertyType;
    Long count;
}
