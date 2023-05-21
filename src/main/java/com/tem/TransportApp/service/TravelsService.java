package com.tem.TransportApp.service;

import com.tem.TransportApp.domain.Location;
import com.tem.TransportApp.domain.Travel;
import com.tem.TransportApp.domain.Vehicle;
import com.tem.TransportApp.dto.LocationDto;
import com.tem.TransportApp.dto.TravelsDto;
import com.tem.TransportApp.dto.VehicleDto;
import com.tem.TransportApp.dto.response.GenericResponse;
import com.tem.TransportApp.repository.LocationRepository;
import com.tem.TransportApp.repository.TravelsRepository;
import com.tem.TransportApp.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TravelsService {

    private final TravelsRepository travelsRepository;
    private final LocationRepository locationRepository;
    private final VehicleRepository vehicleRepository;

    public GenericResponse addLocation(List<LocationDto> locationDto) {
        List<Location> alreadyExist  = new ArrayList<>();
        locationDto.forEach(location -> {
            Location existCheck = locationRepository.findLocationByName(location.getStateName());
            if(existCheck == null){
                Location newLocation = new Location();
                newLocation.setName(location.getStateName());
                newLocation.setSpecificPack(location.getSpecificPack());
                locationRepository.save(newLocation);

            }

            else{
                alreadyExist.add(locationRepository.findLocationByName(location.getStateName()));
            }

        });


//        if(alreadyExist!= null){
//            return new GenericResponse("00",
//                    "Some locations already exist",alreadyExist,null);
//        }
        return new GenericResponse("00","Locations saved",null,null);
    }

    public GenericResponse addVehicle(List<VehicleDto> vehicleDtos) {
        log.info("API to addVehicle");
        vehicleDtos.forEach(vehicleDto -> {
            Vehicle checkIfExisit = vehicleRepository
                    .findVehicleByNameOfVechileProvider(vehicleDto.getNameVehicleProvider());

            if(checkIfExisit == null){
                Vehicle newVehicle = new Vehicle();

                LocalDate date = LocalDate.now();
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String dateUpdated = date.format(dateTimeFormatter);

                newVehicle.setNameOfVechileProvider(vehicleDto.getNameVehicleProvider());
                newVehicle.setVehicleType(vehicleDto.getVehicleType());
                newVehicle.setPlateNo(vehicleDto.getPlateNo());
                newVehicle.setNumberOfSits(vehicleDto.getNoOfSits());
                newVehicle.setDateAdded(dateUpdated);
                vehicleRepository.save(newVehicle);
            }
        });
        if(vehicleDtos.size()>1){
            return new GenericResponse("00","Vehicles Saved",null,null);
        }
        return new GenericResponse("00", "Vehicle Saved", null,null);
    }

    public GenericResponse addTravels(TravelsDto travelsDto) {
        log.info("API TO ADD TRAVELS");
        //session to get current user
//        Travel checkTravels = travelsRepository.findTravelByUsers();
        Vehicle vehicle = vehicleRepository.
                findVehicleByNameOfVechileProvider(travelsDto.getNameOfVechileProvider());
        Location location = locationRepository
                .findLocationByNameAndAndSpecificPack(travelsDto.getStateName(),travelsDto.getSpecificPack());
        if(location == null){
            return new GenericResponse("11","location does not exist",null,null);
        } else if (vehicle == null) {
            return new GenericResponse("11","vehicle does not exist",null,null);
        }
        Travel newTravels = new Travel();
            newTravels.getVehicle().add(vehicle);
            newTravels.getLocations().add(location);
            newTravels.setPrice(travelsDto.getPrice());
            newTravels.setNoOfSitters(travelsDto.getNoOfSitters());
            travelsRepository.save(newTravels);

        return new GenericResponse("00", "Location Saved","null","null");
    }

    public GenericResponse seeTravels(String location) {
        log.info("API to See Travel based on location");
        Location location1 = locationRepository.findLocationByName(location);
        List<Travel> getTravels = travelsRepository.findTravelsByLocations(location1);
        if(getTravels == null){
            return  new GenericResponse("00"
                    ,"There are currently no travels to "+location,
                    null,null);
        }
        return new GenericResponse("00"
                ,"The travels availabe in " + location
                , getTravels,null);
    }
}
