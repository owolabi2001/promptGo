package com.tem.TransportApp.webRest;


import com.tem.TransportApp.dto.TravelsDto;
import com.tem.TransportApp.dto.VehicleDto;
import com.tem.TransportApp.dto.response.GenericResponse;
import com.tem.TransportApp.service.TravelsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/travels")
@RequiredArgsConstructor
public class TravelsController {

    // This API endpoints are restricted to ADMIN and TRANSPORTPROVIDER

    private final TravelsService travelsService;

    @PostMapping("/addTravels/{transportProvider}")
    public ResponseEntity<GenericResponse> addTravels(
            @RequestBody TravelsDto travelsDto
            ,@PathVariable String transportProvider){
        // get the current transportprovider using session
        GenericResponse response = travelsService.addTravels(travelsDto);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @PostMapping("/addVehicle")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GenericResponse> registerVehicle(@RequestBody List<VehicleDto> vehicleDtos){
        GenericResponse response= travelsService.addVehicle(vehicleDtos);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/see-Available-Travels/{locationName}")
    public ResponseEntity<GenericResponse> seeAvailableTravels(@PathVariable String locationName){
        return new ResponseEntity<>(travelsService.seeTravels(locationName),HttpStatus.ACCEPTED);

    }

    // Remember to add endpoints to list buses going to a specific location

}
