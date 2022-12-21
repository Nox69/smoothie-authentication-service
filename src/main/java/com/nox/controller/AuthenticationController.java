package com.nox.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nox.model.Customer;
import com.nox.resources.AuthenticationRequest;
import com.nox.resources.AuthenticationResponse;
import com.nox.resources.LoggedInUser;
import com.nox.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/api")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping(value = "/auth/register")
    public ResponseEntity<AuthenticationResponse> createUser(@Valid @RequestBody AuthenticationRequest request) {
        return new ResponseEntity<>(authenticationService.registerCustomer(request), HttpStatus.CREATED);
    }

    @GetMapping(value = "/customers")
    public ResponseEntity<List<Customer>> retrieveUsers() {
        return ResponseEntity.ok(authenticationService.retrieveUsers());
    }
    
    @PostMapping(value = "/auth/login")
    public ResponseEntity<LoggedInUser> loginUser(@RequestBody AuthenticationRequest request) {
        return new ResponseEntity<>(authenticationService.loginCustomer(request), HttpStatus.OK);
    }

}
