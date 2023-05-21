package com.tem.TransportApp.repository;

import com.tem.TransportApp.domain.Vehicle;
import com.tem.TransportApp.dto.VehicleDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,Long> {
    Vehicle findVehicleByNameOfVechileProvider(String vehicle);


}
