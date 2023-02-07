package com.miu.waafinalproject.model.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class PropertyApplicationRequestModel {
    String status;
    String remarks;
    Double offerPrice;
    UUID propertyId;
}
