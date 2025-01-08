package com.ecommerceCatalog.ecommerceCatalog.controller;

import com.ecommerceCatalog.ecommerceCatalog.model.dtos.LoginUserDto;
import com.ecommerceCatalog.ecommerceCatalog.model.dtos.RegisterUserDto;
import com.ecommerceCatalog.ecommerceCatalog.model.entities.User;
import com.ecommerceCatalog.ecommerceCatalog.model.responses.LoginResponse;
import com.ecommerceCatalog.ecommerceCatalog.service.AuthenticationService;
import com.ecommerceCatalog.ecommerceCatalog.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/health")
    public ResponseEntity<String> register() {

        return ResponseEntity.ok("Authentication Service Working Properly");
    }

    @PostMapping("/signup")
    public ResponseEntity<LoginResponse> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        //Sending back jwt
        return ResponseEntity.ok(generateLoginResponse(registeredUser));
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        System.out.println("Minimo");
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        return ResponseEntity.ok(generateLoginResponse(authenticatedUser));
    }

    private LoginResponse generateLoginResponse(User authenticatedUser){
        String jwtToken = jwtService.generateToken(authenticatedUser);

        return LoginResponse.builder()
                .token(jwtToken)
                .expiresIn(jwtService.getExpirationTime())
                .build();
    }

}