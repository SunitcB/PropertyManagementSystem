package com.miu.waafinalproject.model.requestDTO;

import com.miu.waafinalproject.utils.enums.PropertyStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class PropertyRequestModel {
    UUID id;
    String title;
    String description;
    Short bed;
    Short bath;
    Integer builtYear;
    Boolean hasBasement;
    Boolean hasParking;
    Float area;
    String features;
    String propertyOption;
    String propertyType;
    String street;
    String city;
    String state;
    Integer zipcode;
    Long ownerId;
    Double price;
    String propertyStatus = PropertyStatus.AVAILABLE.toString();
    Boolean isActive = true;
}
