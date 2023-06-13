package com.tem.TransportApp.webRest;


import com.tem.TransportApp.dto.LocationDto;
import com.tem.TransportApp.dto.VehicleDto;
import com.tem.TransportApp.dto.response.GenericResponse;
import com.tem.TransportApp.service.TravelsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/admin/v1")
@RequiredArgsConstructor
public class AdminController {

    private final TravelsService travelsService;
    @PostMapping("/addLocations")
    @ResponseStatus(code = HttpStatus.OK)
//    @PreAuthorize("hasRole('ADMIN')")
//    @Secured("ROLE_ADMIN")
    public ResponseEntity<GenericResponse> addLocations(@RequestBody List<LocationDto> locationDto){
        GenericResponse response= travelsService.addLocation(locationDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
