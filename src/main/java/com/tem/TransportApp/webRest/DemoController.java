package com.tem.TransportApp.webRest;


import com.tem.TransportApp.dto.response.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "demo")
public class DemoController {

    @GetMapping
    public ResponseEntity<GenericResponse> simpleAPi(){
        return new ResponseEntity<>(
                new GenericResponse("11","Secured Endpoint",null,null),
                HttpStatus.ACCEPTED);
    }

}
