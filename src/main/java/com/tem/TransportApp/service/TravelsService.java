package com.tem.TransportApp.service;

import com.tem.TransportApp.domain.Location;
import com.tem.TransportApp.dto.LocationDto;
import com.tem.TransportApp.dto.response.GenericResponse;
import com.tem.TransportApp.repository.LocationRepository;
import com.tem.TransportApp.repository.TravelsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class TravelsService {

    private final TravelsRepository travelsRepository;
    private final LocationRepository locationRepository;

    public GenericResponse addLocation(List<LocationDto> locationDto) {
        List<String> alreadyExist  = new ArrayList<>();
        locationDto.forEach(location -> {
            Location existCheck = locationRepository.findLocationByName(location.getStateName());
            if(existCheck == null){
                Location newLocation = new Location();
                newLocation.setName(location.getStateName());
                newLocation.setSpecificPack(location.getSpecificPack());
                locationRepository.save(newLocation);
            }
            else{
                alreadyExist.add(location.getStateName());
            }
        });
        if(alreadyExist == null){
            return new GenericResponse("00","Locations saved",null,null);
        }
        return new GenericResponse("00",
                "Some locations already exist",alreadyExist,null);
    }
}
