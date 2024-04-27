package br.com.builders.escolar.model.DTO;

import br.com.builders.escolar.model.enums.FamilyTypeEnum;
import br.com.builders.escolar.model.enums.SexEnum;
import br.com.builders.escolar.model.enums.SituationsStudentEnum;
import lombok.*;
import org.springframework.core.io.Resource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FullStudentDTO {
    //============== student ==============
    private Long id;
    private String name;
    private String identity;
    private String cpf;
    private LocalDate dateOfBirth;
    private String nationality;
    private String naturalness;
    private SexEnum sex;
    private String email;
    private String cep;
    private String address;
    private String phone;
    private String neighborhood;
    private String numberHouse;
    private String city;
    private String state;
    private String country;

    private String emailPersonResponsible;
    private String responsible;
    private String father;
    private String mother;
    private Resource imageProfile;
    private Resource cpfFile;
    private Resource rgFile;
    private Resource proofOfAddressFile;
    private SituationsStudentEnum situation;
    private LocalDateTime createdAt;

    //============== family ==============

    private Long familyId;
    private String familyName;
    private String familyIdentity;
    private String familyCpf;
    private LocalDate familyDateOfBirth;
    private String familyNationality;
    private String familyNaturalness;
    private SexEnum familySex;
    private String familyEmail;
    private String familyCep;
    private String familyAddress;
    private String familyPhone;
    private String familyNeighborhood;
    private String familyNumberHouse;
    private String familyCity;
    private String familyState;
    private String familyCountry;
    private String familyWorkAddress;
    private String familyOccupation;
    private FamilyTypeEnum familyType;
    private Resource familyCpfFile;
    private Resource familyRgFile;
    private Resource familyProofOfAddress;
    private LocalDateTime familyCreatedAt;

    //============== financial responsible ==============

    private Long financialId;
    private String financialName;
    private String financialIdentity;
    private String financialCpf;
    private LocalDate financialDateOfBirth;
    private String financialNationality;
    private String financialNaturalness;
    private SexEnum financialSex;
    private String financialEmail;
    private String financialCep;
    private String financialAddress;
    private String financialPhone;
    private String financialNeighborhood;
    private String financialNumberHouse;
    private String financialCity;
    private String financialState;
    private String financialCountry;
    private String financialWorkAddress;
    private String financialOccupation;
    private FamilyTypeEnum financialType;
    private Resource financialCpfFile;
    private Resource financialRgFile;
    private Resource financialProofOfAddressFile;
    private LocalDateTime financialCreatedAt;


    //============== fixed health ==============

    private Long fixedHealthId;
    private List<String> allergies;
    private String healthInsurance;
    private String bloodGroup;
    private String medicalClinic;
    private Long quantityBrothers;
    private boolean toGoOutAuthorization;
    private LocalDateTime fixedHealthCreatedAt;
}

