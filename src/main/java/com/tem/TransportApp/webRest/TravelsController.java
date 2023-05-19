package com.tem.TransportApp.webRest;

import com.tem.TransportApp.domain.Role;
import com.tem.TransportApp.dto.LocationDto;
import com.tem.TransportApp.dto.VehicleDto;
import com.tem.TransportApp.dto.response.GenericResponse;
import com.tem.TransportApp.service.TravelsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
// remember to restrict this api's to roles
@RequestMapping(path = "/travels")
@RequiredArgsConstructor
public class TravelsController {

    private final TravelsService travelsService;

    @PostMapping("/addLocations")
//    @PreAuthorize("hasRole('ADMIN')")
//    @Secured("ROLE_ADMIN")
    public ResponseEntity<GenericResponse> addLocations(@RequestBody List<LocationDto> locationDto){
        GenericResponse response= travelsService.addLocation(locationDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/addVehicle")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GenericResponse> addVehicle(@RequestBody List<VehicleDto> vehicleDtos){
        GenericResponse response= travelsService.addVehicle(vehicleDtos);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
