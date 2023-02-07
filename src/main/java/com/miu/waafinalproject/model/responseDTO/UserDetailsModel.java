package com.miu.waafinalproject.model.responseDTO;

import com.miu.waafinalproject.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserDetailsModel {
    String email;
    String firstName;
    String lastName;
    String middleName;
    String address;
    String phone;
    List<Role> roles;
    String username;
}
