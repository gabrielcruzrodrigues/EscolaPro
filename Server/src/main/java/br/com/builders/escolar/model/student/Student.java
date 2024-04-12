package br.com.builders.escolar.model.student;

import br.com.builders.escolar.model.Person;
import br.com.builders.escolar.model.enums.ShiftEnum;
import br.com.builders.escolar.model.enums.SituationsStudentEnum;
import br.com.builders.escolar.model.files.BasicFilesStudents;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Student extends Person {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SituationsStudentEnum situation;

    @Email
    @NotBlank
    @Column(nullable = false, length = 100)
    private String emailPersonResponsible;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String responsible;

    @Column(length = 50)
    private String father;

    @Column(length = 50)
    private String mother;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private List<ShiftEnum> shifts;

    @OneToOne(mappedBy = "student")
    private BasicFilesStudents imageProfile;

    @OneToMany(mappedBy = "student")
    private List<BasicFilesStudents> cpfFile;

    @OneToMany(mappedBy = "student")
    private List<BasicFilesStudents> rgFile;

    @OneToMany(mappedBy = "student")
    private List<BasicFilesStudents> proofOfAddressFile;

    @OneToMany(mappedBy = "student")
    private List<Family> family;

    @OneToOne(mappedBy = "student")
    private FixedHealth fixedHealth;

    @OneToOne(mappedBy = "student")
    private FinancialResponsible financialResponsible;
}
