package com.tem.TransportApp.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "_vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //Specific name of Transport provider
    private String nameOfVechileProvider;
    private String vehicleType;
    private Integer numberOfSits;
    private String plateNo;
    private String dateAdded;

    public Vehicle(String nameOfVechileProvider, String vehicleType) {
        this.nameOfVechileProvider = nameOfVechileProvider;
        this.vehicleType = vehicleType;
    }
}
