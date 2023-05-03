package com.tem.TransportApp.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.tem.TransportApp.configuration.jwt.JwtService;
import com.tem.TransportApp.domain.AppUser;
import com.tem.TransportApp.domain.Attachment;
import com.tem.TransportApp.domain.Token;
import com.tem.TransportApp.domain.TokenType;
import com.tem.TransportApp.dto.AuthenticationRequest;
import com.tem.TransportApp.dto.RegisterRequest;
import com.tem.TransportApp.dto.response.AuthenticationResponse;
import com.tem.TransportApp.dto.response.GenericResponse;
import com.tem.TransportApp.repository.AppUserRepository;
import com.tem.TransportApp.repository.AttachmentRepository;
import com.tem.TransportApp.repository.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {


    private final AppUserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final AttachmentRepository attachmentRepository;


    public GenericResponse register(RegisterRequest request) {
        AppUser userChecker =  userRepository.findAppUserByWebmail(request.getEmail());

        if(userChecker == null){

            var user = AppUser.builder()
                    .name(request.getName())
                    .webmail(request.getEmail())
                    .regNo(request.getRegNo())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(request.getRole())
                    .build();
            var savedUser = userRepository.save(user);
//        var jwtToken = jwtService.generateToken(user);
//        var refreshToken = jwtService.generateRefreshToken(user);
//        saveUserToken(savedUser, jwtToken);

            return GenericResponse.builder()
                    .code("00")
                    .message("User with webmail: "+ user.getWebmail()+ " has been added Sucessfully")
                    .data(user)
                    .metaData(null)
                    .build();

        }
        return GenericResponse.builder()
                .code("11")
                .data(userChecker)
                .message("user with webmail: " + userChecker.getWebmail() +" already exist")
                .metaData(null)
                .build();

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByWebmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void saveUserToken(AppUser user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(AppUser user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.userRepository.findByWebmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

    public GenericResponse saveAttachment(String webmail, MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        AppUser user = userRepository.findAppUsersByWebmail(webmail);

        if(user==null){
            return new GenericResponse(
                    "11",
                    "You need to register a User First",
                    null,
                    null
            );
        }
        try{
            if(fileName.contains("..")){
                return new GenericResponse(
                        "11",
                        "Filename contains invalid path sequence "+fileName,
                        null,
                        null
                );
            }
            Attachment attachment = new Attachment(fileName,file.getContentType(),file.getBytes());
            attachmentRepository.save(attachment);
            user.setAttachment(attachment);
            userRepository.save(user);
            return new GenericResponse(
                    "00",
                    "File with the name: " + fileName+" saved succesfully",
                    file.getBytes(),
                    null
            );

        }
        catch (Exception e){
            return new GenericResponse(
                    "11",
                    "File not saved",
                    null,null
            );
        }


    }

}
