package com.miu.waafinalproject.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PropertyView {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String ipAddress;
    @ManyToOne
    @JsonBackReference
    Property property;
}
