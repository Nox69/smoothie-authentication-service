package com.nox.service;

import java.util.List;

import com.nox.model.Customer;
import com.nox.resources.AuthenticationRequest;
import com.nox.resources.AuthenticationResponse;
import com.nox.resources.LoggedInUser;

public interface AuthenticationService {

    public AuthenticationResponse registerCustomer(AuthenticationRequest request);

    public List<Customer> retrieveUsers();
    
    public LoggedInUser loginCustomer(AuthenticationRequest request);

}
