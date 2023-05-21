package com.tem.TransportApp.repository;

import com.tem.TransportApp.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location,Long> {
    Location findLocationByName(String locationName);
    Location findLocationByNameAndAndSpecificPack(String stateName,String specificPark);
}
