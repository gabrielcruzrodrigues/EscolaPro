package br.com.builders.escolar.model.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record UpdateFixedHealthDTO(
        @NotNull
        List<String> allergies,
        @NotBlank
        String healthInsurance,
        @NotBlank
        String bloodGroup,
        @NotBlank
        String medicalClinic,
        @NotNull
        Long quantityBrothers,
        @NotNull
        boolean toGoOutAuthorization,
        @NotNull
        boolean active,
        @NotNull
        LocalDateTime createdAt,
        @NotNull
        Long id
) {
}
