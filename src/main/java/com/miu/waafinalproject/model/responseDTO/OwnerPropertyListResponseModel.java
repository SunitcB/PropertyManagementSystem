package com.miu.waafinalproject.model.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class OwnerPropertyListResponseModel {
    UUID id;
    String title;
    String description;
    Double price;
    String address;
    String propertyOption;
    Short bed;
    Short bath;
    Integer builtYear;
    Integer offerCount;
    Integer viewCount;
    String propertStatus;
    Boolean isActive;
}
