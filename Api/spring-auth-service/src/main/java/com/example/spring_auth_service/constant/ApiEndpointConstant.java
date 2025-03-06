package com.example.spring_auth_service.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiEndpointConstant {
    private static final String API_VERSION = "/api/v1";

    public static final String AUTH_ENDPOINT = API_VERSION + "/auth";
}
