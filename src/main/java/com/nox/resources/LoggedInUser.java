package com.nox.resources;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class LoggedInUser {

    private String name;
    private String token;
    private boolean isAuthenticated;

}
