package br.com.builders.escolar.model.DTO;

import br.com.builders.escolar.model.enums.FamilyTypeEnum;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public record CreateFamilyStudentDTO(
        String name,
        String email,
        String identity,
        String cpf,
        String nationality,
        String naturalness,
        String country,
        LocalDate dateOfBirth,
        String address,
        String workAddress,
        String occupation,
        String neighborhood,
        String numberHouse,
        String city,
        String phone,
        String state,
        String cep,
        FamilyTypeEnum type,
        MultipartFile cpfFile,
        MultipartFile rgFile,
        MultipartFile proofOfAddress,
        Long studentId
) {
}
