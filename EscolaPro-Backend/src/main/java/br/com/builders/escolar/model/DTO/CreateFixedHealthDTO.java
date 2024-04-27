package br.com.builders.escolar.model.DTO;

import br.com.builders.escolar.model.student.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record CreateFixedHealthDTO(
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
        Long studentId
) {
}
