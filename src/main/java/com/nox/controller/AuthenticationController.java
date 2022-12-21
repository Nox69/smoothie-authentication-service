package com.nox.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

/**
 * This is a controller (interface adapter) used by the web interface. Each controller is responsible for one functionality only, following this way
 * the Single Responsibility Principle.
 *
 * The REST controllers consume and produce JSON by default.
 */
@RestController
@RequestMapping("/v1/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {

    /**
     * Constructor Injection Used
     */
    private final AuthenticationService authenticationService;

    /**
     * This method is executed when the customers perform a `POST` request to the `/auth/register` endpoint. The request's JSON body is converted into
     * the `request` param. This endpoint returns a `201 CREATED` status code to the client after successfully creating the Customer in DB
     * 
     */
    @PostMapping(value = "/auth/register")
    public ResponseEntity<AuthenticationResponse> createUser(@Valid @RequestBody AuthenticationRequest request) {
        return new ResponseEntity<>(authenticationService.registerCustomer(request), HttpStatus.CREATED);
    }

    // TODO Dummy Endpoint to be removed as it's added for testing purpose
    @GetMapping(value = "/customers")
    public ResponseEntity<List<Customer>> retrieveUsers() {
        return ResponseEntity.ok(authenticationService.retrieveUsers());
    }

    /**
     * This method is executed when the employees perform a `POST` request to the `/auth/login` endpoint. The request's JSON body is converted into
     * the `request` param. This endpoint returns a `200 OK` status code to the client along with signed JWT Token
     */
    @PostMapping(value = "/auth/login")
    public ResponseEntity<LoggedInUser> loginUser(@RequestBody AuthenticationRequest request) {
        return new ResponseEntity<>(authenticationService.loginCustomer(request), HttpStatus.OK);
    }

}
