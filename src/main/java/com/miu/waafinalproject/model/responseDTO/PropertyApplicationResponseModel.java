package com.miu.waafinalproject.model.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PropertyApplicationResponseModel {
    Long id;
    String status;
    String remarks;
    Double offerPrice;
    String offeredBy;
    String propertyTitle;
    String owner;
}
