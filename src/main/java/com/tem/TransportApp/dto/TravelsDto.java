package com.tem.TransportApp.dto;

import lombok.*;


@Data
@RequiredArgsConstructor
public class TravelsDto {


    private String price;
    private String noOfSitters;
    private String stateName;
    private String specificPack;
    private String vehicleType;
    private String nameOfVechileProvider;
}
