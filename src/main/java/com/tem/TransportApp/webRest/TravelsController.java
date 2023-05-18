package com.tem.TransportApp.webRest;

import com.tem.TransportApp.dto.LocationDto;
import com.tem.TransportApp.dto.response.GenericResponse;
import com.tem.TransportApp.service.TravelsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(path = "/travels")
@RequiredArgsConstructor
public class TravelsController {

    private final TravelsService travelsService;

    @PostMapping("/addLocations")
    public ResponseEntity<GenericResponse> addLocations(@RequestBody List<LocationDto> locationDto){
        GenericResponse response= travelsService.addLocation(locationDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
