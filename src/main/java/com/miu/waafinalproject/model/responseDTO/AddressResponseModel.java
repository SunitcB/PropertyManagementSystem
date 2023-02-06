package com.miu.waafinalproject.model.responseDTO;

import com.miu.waafinalproject.domain.Address;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class AddressResponseModel {
    String street;
    String state;
    Integer zipcode;

    public AddressResponseModel(Address address){
        this.street = address.getStreet();
        this.state = address.getState();
        this.zipcode = address.getZipcode();
    }
    @Override
    public String toString() {
        return street + ", " + state + ", " + zipcode;
    }
}
