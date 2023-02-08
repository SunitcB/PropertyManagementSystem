package com.miu.waafinalproject.model.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDetailsModel {
    String email;
    String firstName;
    String lastName;
    String middleName;
    String role;
    String username;
}
