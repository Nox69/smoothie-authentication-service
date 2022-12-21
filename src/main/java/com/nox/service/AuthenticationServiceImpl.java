package com.nox.service;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nox.exception.CustomerAlreadyExistsException;
import com.nox.exception.CustomerNotFoundException;
import com.nox.model.Customer;
import com.nox.repository.AuthenticationRepository;
import com.nox.resources.AuthenticationRequest;
import com.nox.resources.AuthenticationResponse;
import com.nox.resources.LoggedInUser;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
/**
 * the `@Transactional` annotation grants that all db operations inside this method will be executed inside a transaction and that all operations will
 * succeed or all operations will be rolled back.
 */
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final String ADMIN_ROLE = "business-owner";
    private static final String USERS_ROLE = "end-user";
    private static final String SECRET_KEY = "smoothie-service"; // TODO to make it more dynamic

    // List of Allowed Admins as of now
    private static final List<String> ADMIN_USER_LIST = Collections.unmodifiableList(List.of("bharatch@yahoo.in", "admin@gmail.com",
            "bharat@gmail.com", "amar@gmail.com", "bartosz@gmail.com", "bartosz@yahoo.com", "bharat@yahoo.com", "admin@yahoo.com"));

    private final AuthenticationRepository authenticationRepository;

    @Override
    public AuthenticationResponse registerCustomer(AuthenticationRequest request) {
        // Assign the role accordingly to the Customer Id
        String role = ADMIN_USER_LIST.stream().filter(id -> id.equals(request.getEmailId())).findAny().isPresent() ? ADMIN_ROLE : USERS_ROLE;
        Optional<Customer> customer = authenticationRepository.findAll().stream().filter(x -> x.getCustomerEmailId().equals(request.getEmailId()))
                .findAny();
        if (customer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer Already Registered with the Email ID");
        }
        Customer createdUser = authenticationRepository.save(Customer.builder().customerName(request.getName()).customerEmailId(request.getEmailId())
                .customerPassword(request.getPassword()).customerRole(role).customerAddedDate(new Date()).build());
        return AuthenticationResponse.builder().emailId(createdUser.getCustomerEmailId()).userId(createdUser.getCustomerId())
                .name(createdUser.getCustomerName()).assignedRole(createdUser.getCustomerRole()).build();
    }

    @Override
    public List<Customer> retrieveUsers() {
        // authenticationRepository.deleteAll(); //TODO to refresh the database records when required
        return authenticationRepository.findAll();
    }

    @Override
    public LoggedInUser loginCustomer(AuthenticationRequest request) {
        Optional<Customer> loggedInCustomer = authenticationRepository.findAll().stream()
                .filter(x -> x.getCustomerEmailId().equals(request.getEmailId()) && x.getCustomerPassword().equals(request.getPassword())).findAny();
        if (loggedInCustomer.isEmpty()) {
            throw new CustomerNotFoundException("Invalid Credentials Entered for : " + request.getEmailId());
        }
        return LoggedInUser.builder().name(loggedInCustomer.get().getCustomerName()).token(generateToken(loggedInCustomer.get())).build();
    }

    // generate token for Customer
    private String generateToken(Customer customer) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", customer.getCustomerRole());
        claims.put("customerId", customer.getCustomerId());
        claims.put("customerName", customer.getCustomerName());
        return doGenerateToken(claims, customer.getCustomerId().toString());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
    }

}
