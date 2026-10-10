package com.bootlabs.authapi.dto;

public record LoginResponse(String username, String token, String pod) {
}
