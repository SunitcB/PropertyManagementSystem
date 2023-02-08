package com.miu.waafinalproject.model.responseDTO;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
public class PropertyListResponseModel {
    UUID id;
    String title;
    String description;
    Double price;
    String address;
    String image;
    String propertyOption;
    Short bed;
    Short bath;
    Integer builtYear;
    String status;
    long propertyViewCount;
}
