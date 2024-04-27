package br.com.builders.escolar.model.DTO;

import br.com.builders.escolar.model.enums.FamilyTypeEnum;
import br.com.builders.escolar.model.enums.SexEnum;
import br.com.builders.escolar.model.enums.SituationsStudentEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record FullStudentWith2FamilyDTO(
        //============== student ==============
        Long id,
        String name,
        String identity,
        String cpf,
        LocalDate dateOfBirth,
        String nationality,
        String naturalness,
        SexEnum sex,
        String email,
        String cep,
        String address,
        String phone,
        String neighborhood,
        String numberHouse,
        String city,
        String state,
        String country,

        String emailPersonResponsible,
        String responsible,
        String father,
        String mother,
//        Resource imageProfile,
//        Resource cpfFile,
//        Resource rgFile,
//        Resource proofOfAddress,
        SituationsStudentEnum situation,
        LocalDateTime createdAt,

        //============== family ==============

        Long familyId,
        String familyName,
        String familyIdentity,
        String familyCpf,
        LocalDate familyDateOfBirth,
        String familyNationality,
        String familyNaturalness,
        SexEnum familySex,
        String familyEmail,
        String familyCep,
        String familyAddress,
        String familyPhone,
        String familyNeighborhood,
        String familyNumberHouse,
        String familyCity,
        String familyState,
        String familyCountry,
        String familyWorkAddress,
        String familyOccupation,
        FamilyTypeEnum familyType,
//        Resource familyCpfFile,
//        Resource familyRgFile,
//        Resource familyProofOfAddress,
        LocalDateTime familyCreatedAt,

        //============== family 2 ==============

        Long familyId2,
        String familyName2,
        String familyIdentity2,
        String familyCpf2,
        LocalDate familyDateOfBirth2,
        String familyNationality2,
        String familyNaturalness2,
        SexEnum familySex2,
        String familyEmail2,
        String familyCep2,
        String familyAddress2,
        String familyPhone2,
        String familyNeighborhood2,
        String familyNumberHouse2,
        String familyCity2,
        String familyState2,
        String familyCountry2,
        String familyWorkAddress2,
        String familyIOccupation2,
        FamilyTypeEnum familyType2,
//        Resource familyCpfFile2,
//        Resource familyRgFile2,
//        Resource familyProofOfAddress2,
        LocalDateTime familyCreatedAt2,

        //============== financial responsible ==============

        Long financialId,
        String financialName,
        String financialIdentity,
        String financialCpf,
        LocalDate financialDateOfBirth,
        String financialNationality,
        String financialNaturalness,
        SexEnum financialSex,
        String financialEmail,
        String financialCep,
        String financialAddress,
        String financialPhone,
        String financialNeighborhood,
        String financialNumberHouse,
        String financialCity,
        String financialState,
        String financialCountry,
        String financialWorkAddress,
        String financialOccupation,
        FamilyTypeEnum financialType,
//        Resource financialCpfFile,
//        Resource financialRgFile,
//        Resource financialProofOfAddress,
        LocalDateTime financialCreatedAt,


        //============== fixed health ==============

        Long fixedHealthId,
        List<String> allergies,
        String healthInsurance,
        String bloodGroup,
        String medicalClinic,
        Long quantityBrothers,
        boolean toGoOutAuthorization,
        LocalDateTime fixedHealthCreatedAt
) {
}
