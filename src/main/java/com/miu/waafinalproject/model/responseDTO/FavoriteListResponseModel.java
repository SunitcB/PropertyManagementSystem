package com.miu.waafinalproject.model.responseDTO;

import com.miu.waafinalproject.domain.Property;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class FavoriteListResponseModel {
    Long id;
    PropertyListResponseModel property;
}
