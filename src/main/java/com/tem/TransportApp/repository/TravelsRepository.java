package com.tem.TransportApp.repository;

import com.tem.TransportApp.domain.AppUser;
import com.tem.TransportApp.domain.Location;
import com.tem.TransportApp.domain.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravelsRepository extends JpaRepository<Travel,Long> {
    List<Travel> findTravelsByLocations(Location location);
//    Travel findTravel
    Travel findTravelByUsers(AppUser appUser);
}
