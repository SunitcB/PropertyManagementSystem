package com.miu.waafinalproject.model.responseDTO;

import com.miu.waafinalproject.domain.Address;
import com.miu.waafinalproject.domain.PropertyDetail;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PdfResponseModel {
    private String buyerName;
    private String sellerName;
    private LocalDate contractDate;
    private PropertyDetail propertyDetail;
    private Address address;
    private double costPrice;
    private double tax;
    private double sellingPrice;
}
