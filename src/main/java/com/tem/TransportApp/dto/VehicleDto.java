package com.tem.TransportApp.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class VehicleDto {

    private String nameVehicleProvider;
    private String vehicleType;
    private int noOfSits;
    private String plateNo;
}
