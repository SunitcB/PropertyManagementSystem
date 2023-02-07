package com.miu.waafinalproject.model.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseModel {
    String accessToken;
    String refreshToken;
}
