package com.miu.waafinalproject.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PropertyApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String status;
    String remarks;
    Double offerPrice;
    @ManyToOne
    @JsonBackReference
    Users users;
    @ManyToOne
    @JsonBackReference
    Property property;
    LocalDate submissionDate;
}
