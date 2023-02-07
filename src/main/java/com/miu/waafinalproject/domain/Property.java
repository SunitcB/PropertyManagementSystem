package com.miu.waafinalproject.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Property {
    @Id
    @GeneratedValue
    @UuidGenerator
    UUID id;
//    @GeneratedValue(strategy = GenerationType.UUID)
//    @UuidGenerator
//    UUID uuid;
    String title;
    @OneToOne
    PropertyDetail propertyDetail;
    @OneToOne
    PropertyOption propertyOption;
    @OneToOne
    PropertyType propertyType;
    @OneToOne
    Address address;
    @OneToOne
    PropertyView propertyView;
    @OneToMany
    @JsonManagedReference
    List<Favorite> favorites;
    @OneToMany(mappedBy = "property")
    @JsonManagedReference
    List<PropertyApplication> applications;
    @OneToOne
    Assets assets;
    @ManyToOne
    @JsonBackReference
    Users owner;
}
