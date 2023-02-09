package com.miu.waafinalproject.model.responseDTO;

import com.miu.waafinalproject.domain.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class PropertyResponseModel {
    UUID id;
    String title;
    PropertyDetail propertyDetail;
    String propertyOption;
    Double price;
    String propertyType;
    Address address;
    long propertyViewCount;
    String image;
    Integer builtYear;
    String propertyStatus;
}
