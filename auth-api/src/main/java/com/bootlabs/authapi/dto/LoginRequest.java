package com.bootlabs.authapi.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "username must not be blank") String username
) {
}
