package br.com.builders.escolar.model.DTO;

import br.com.builders.escolar.model.enums.RoleEnum;
import br.com.builders.escolar.model.enums.SexEnum;
import br.com.builders.escolar.model.enums.ShiftEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

public record StudentCreateData(
        @NotBlank
        String name,
        @NotBlank
        String identity,
        @NotBlank
        String cpf,
        @NotBlank
        Date dateOfBirth,
        @NotBlank
        String nationality,
        @NotBlank
        String naturalness,
        @NotNull
        SexEnum sex,
        @NotBlank
        String email,
        @NotBlank
        String cep,
        @NotBlank
        String address,
        @NotBlank
        String phone,
        @NotBlank
        String neighborhood,
        @NotBlank
        String city,
        @NotBlank
        String state,
        @NotBlank
        String country,
        @NotBlank
        String emailPersonResponsible,
        @NotBlank
        String responsible,
        @NotBlank
        String father,
        @NotBlank
        String mother,
        @NotNull
        List<ShiftEnum> shifts,
        MultipartFile imageProfile
) {
}
