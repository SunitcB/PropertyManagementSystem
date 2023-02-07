package com.miu.waafinalproject.model.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequestModel {
    String username;
    String password;
}
