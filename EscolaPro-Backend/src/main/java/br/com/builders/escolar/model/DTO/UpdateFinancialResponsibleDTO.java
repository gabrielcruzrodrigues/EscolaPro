package br.com.builders.escolar.model.DTO;

import br.com.builders.escolar.model.enums.FamilyTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public record UpdateFinancialResponsibleDTO(
        @NotBlank
        String name,
        @NotBlank
        String email,
        @NotNull
        LocalDate dateOfBirth,
        @NotBlank
        String identity,
        @NotBlank
        String cpf,
        @NotBlank
        String address,
        @NotBlank
        String workAddress,
        @NotBlank
        String occupation,
        @NotBlank
        String neighborhood,
        @NotBlank
        String numberHouse,
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
        MultipartFile imageProfile,
        MultipartFile cpfFile,
        MultipartFile rgFile,
        MultipartFile proofOfAddress,
        @NotNull
        Long id
) {
}
