package com.my.securityTest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinDTO {
    private String username;
    private String password;
    private String email;
    private String provider;
    private String providerId;
}
