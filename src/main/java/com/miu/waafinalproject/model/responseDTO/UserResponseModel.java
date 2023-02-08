package com.miu.waafinalproject.model.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponseModel {
    Long id;
    String email;
    String firstName;
    String lastName;
    String middleName;
    String address;
    String phone;
    String username;
}
