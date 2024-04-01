package br.com.builders.escolar.security.model;

public record LoginResponseDTO(
        String username,
        String token
) {
}
