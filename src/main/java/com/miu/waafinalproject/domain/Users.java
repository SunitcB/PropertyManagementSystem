package com.miu.waafinalproject.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String email;
    String firstName;
    String lastName;
    String middleName;
    String address;
    String phone;
    String password;
    @OneToMany(fetch = FetchType.EAGER)
    @JsonManagedReference
    List<Role> roles;
    String username;

    @OneToMany(mappedBy = "users")
    @JsonManagedReference
    List<Favorite> favorites;
    @OneToMany(mappedBy = "users")
    @JsonManagedReference
    List<PropertyApplication> propertyApplications;
    @OneToMany(mappedBy = "owner")
    @JsonManagedReference
    List<Property> properties;

    public String getUserFullName() {
        return (this.firstName + " " + ((this.middleName != null) ? this.middleName : "") + " " + this.lastName);
    }
}
