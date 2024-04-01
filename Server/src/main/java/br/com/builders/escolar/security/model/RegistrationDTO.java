package br.com.builders.escolar.security.model;

import jakarta.validation.constraints.NotBlank;

public record RegistrationDTO(
        @NotBlank
        String username,
        @NotBlank
        String password
) {
}
