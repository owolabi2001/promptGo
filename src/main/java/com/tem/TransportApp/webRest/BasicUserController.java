package com.tem.TransportApp.webRest;

import com.tem.TransportApp.dto.response.GenericResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("user/")
public class BasicUserController {
    // Open to all

    @PostMapping("")
    private ResponseEntity<GenericResponse> bookTravels(){
        return null;
    }

    @GetMapping("{location}")
    private ResponseEntity<GenericResponse>seeTravelsAvailable(@PathVariable String location){
//        GenericResponse response =
        return null;
    }

    //API to know the location offered by a particular Transport Provider
}
