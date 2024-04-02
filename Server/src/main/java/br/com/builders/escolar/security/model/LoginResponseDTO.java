package br.com.builders.escolar.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record LoginResponseDTO(
        String username,
        String token,
        @JsonIgnore
        boolean login
) {
}
