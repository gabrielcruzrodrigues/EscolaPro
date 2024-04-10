package br.com.builders.escolar.model.DTO;

import br.com.builders.escolar.model.enums.FamilyTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateFamilyStudentDTO(
        @NotBlank
        String name,
        @NotBlank
        String email,
        @NotNull
        LocalDate dateOfBirth,
        @NotBlank
        String address,
        @NotBlank
        String workAddress,
        @NotBlank
        String occupation,
        @NotBlank
        String neighborhood,
        @NotBlank
        String city,
        @NotBlank
        String phone,
        @NotBlank
        String state,
        @NotBlank
        String cep,
        @NotNull
        FamilyTypeEnum type,
        @NotNull
        Long studentId
) {
}
