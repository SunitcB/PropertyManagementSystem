package com.miu.waafinalproject.model.requestDTO;

import lombok.Data;

@Data
public class UserRequestModel {
    Long id;
    String email;
    String firstName;
    String lastName;
    String middleName;
    String password;
    String username;
    String userRole;
}
