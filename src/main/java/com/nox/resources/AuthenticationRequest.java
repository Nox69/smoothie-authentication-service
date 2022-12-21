package com.nox.resources;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationRequest {

    @NotNull(message = "emailId is required")
    private String emailId;
    @NotNull(message = "password is required")
    private String password;
    @NotNull(message = "name is required")
    private String name;

}
