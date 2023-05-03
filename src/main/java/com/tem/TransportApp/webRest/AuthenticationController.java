package com.tem.TransportApp.webRest;


import com.tem.TransportApp.dto.AuthenticationRequest;
import com.tem.TransportApp.dto.RegisterRequest;
import com.tem.TransportApp.dto.response.AuthenticationResponse;
import com.tem.TransportApp.dto.response.GenericResponse;
import com.tem.TransportApp.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {



    private final AuthenticationService service;


    @PostMapping("/register")
    public ResponseEntity<GenericResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }

    @PostMapping("/addPictureToProvider")
    public ResponseEntity<GenericResponse> addPictureToRegisteredProvider(@RequestParam("webmail") String webmail
            , @RequestParam("file") MultipartFile file
    ) throws IOException {

        return ResponseEntity.ok(service.saveAttachment(webmail,file));

    }

}
