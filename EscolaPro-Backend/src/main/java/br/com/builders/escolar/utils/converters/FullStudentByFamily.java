package br.com.builders.escolar.utils.converters;

import br.com.builders.escolar.model.DTO.CreateFamilyStudentDTO;
import br.com.builders.escolar.model.DTO.FullStudentWith2FamilyDTO;

public class FullStudentByFamily {

    public static CreateFamilyStudentDTO convertToCreateFamilyStudentDTO(FullStudentWith2FamilyDTO fullDTO) {
        return new CreateFamilyStudentDTO(
                fullDTO.familyName(),
                fullDTO.familyEmail(),
                fullDTO.familyIdentity(),
                fullDTO.familyCpf(),
                fullDTO.familyNationality(),
                fullDTO.familyNaturalness(),
                fullDTO.familyCountry(),
                fullDTO.familyDateOfBirth(),
                fullDTO.familyAddress(),
                fullDTO.familyWorkAddress(),
                fullDTO.familyOccupation(),
                fullDTO.familyNeighborhood(),
                fullDTO.familyNumberHouse(),
                fullDTO.familyCity(),
                fullDTO.familyPhone(),
                fullDTO.familyState(),
                fullDTO.familyCep(),
                fullDTO.familyType(),
                null,
                null,
                null,
                fullDTO.id()
        );
    }

    public static CreateFamilyStudentDTO convertToCreateFamilyStudentDTO2(FullStudentWith2FamilyDTO fullDTO) {
        return new CreateFamilyStudentDTO(
                fullDTO.familyName2(),
                fullDTO.familyEmail2(),
                fullDTO.familyIdentity2(),
                fullDTO.familyCpf2(),
                fullDTO.familyNationality2(),
                fullDTO.familyNaturalness2(),
                fullDTO.familyCountry2(),
                fullDTO.familyDateOfBirth2(),
                fullDTO.familyAddress2(),
                fullDTO.familyWorkAddress2(),
                fullDTO.familyIOccupation2(),
                fullDTO.familyNeighborhood2(),
                fullDTO.familyNumberHouse2(),
                fullDTO.familyCity2(),
                fullDTO.familyPhone2(),
                fullDTO.familyState2(),
                fullDTO.familyCep2(),
                fullDTO.familyType2(),
                null,
                null,
                null,
                fullDTO.id()
        );
    }
}
