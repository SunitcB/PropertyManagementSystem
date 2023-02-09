package com.miu.waafinalproject.model.responseDTO;

import com.miu.waafinalproject.domain.Role;
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
    String username;
    Role role;
    Boolean isActive;
    Boolean isVerified;
}
