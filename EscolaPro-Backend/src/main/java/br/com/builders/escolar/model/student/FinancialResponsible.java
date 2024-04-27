package br.com.builders.escolar.model.student;

import br.com.builders.escolar.model.Person;
import br.com.builders.escolar.model.enums.FamilyTypeEnum;
import br.com.builders.escolar.model.files.BasicFilesFinancialResponsible;
import br.com.builders.escolar.model.files.BasicFilesStudents;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FinancialResponsible extends Person {

    @Column(length = 100)
    private String workAddress;

    @Column(length = 50)
    private String occupation;

    @Column
    @Enumerated(EnumType.STRING)
    private FamilyTypeEnum type;

    @OneToOne(mappedBy = "cpfFile", cascade = CascadeType.REMOVE)
    private BasicFilesFinancialResponsible cpfFile;

    @OneToOne(mappedBy = "rgFile", cascade = CascadeType.REMOVE)
    private BasicFilesFinancialResponsible rgFile;

    @OneToOne(mappedBy = "proofOfAddress", cascade = CascadeType.REMOVE)
    private BasicFilesFinancialResponsible proofOfAddressFile;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "student_id")
    private Student student;
}
