package com.nox.resources;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationRequest {

    @NotNull(message = "emailId is required")
    @JsonProperty("emailId")
    private String emailId;
    @NotNull(message = "password is required")
    @JsonProperty("password")
    private String password;
    @NotNull(message = "name is required")
    @JsonProperty("name")
    private String name;

}
