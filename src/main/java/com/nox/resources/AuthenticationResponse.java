package com.nox.resources;

import java.util.UUID;

import com.nox.model.Customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AuthenticationResponse {

    private String emailId;
    private String name;
    private String assignedRole;
    private UUID userId;

    public static AuthenticationResponse buildFrom(Customer customer) {
        return AuthenticationResponse.builder().emailId(customer.getCustomerEmailId()).userId(customer.getCustomerId())
                .name(customer.getCustomerName()).assignedRole(customer.getCustomerRole()).build();
    }

}
