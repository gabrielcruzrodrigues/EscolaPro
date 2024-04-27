package br.com.builders.escolar.model.student;

import br.com.builders.escolar.model.Person;
import br.com.builders.escolar.model.enums.SexEnum;
import br.com.builders.escolar.model.enums.ShiftEnum;
import br.com.builders.escolar.model.enums.SituationsStudentEnum;
import br.com.builders.escolar.model.files.BasicFilesStudents;
import br.com.builders.escolar.model.files.ImagesStudents;
import jakarta.persistence.*;
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
    protected SexEnum sex;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SituationsStudentEnum situation;

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

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "student", cascade = CascadeType.REMOVE)
    private ImagesStudents imageProfile;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "cpfFile", cascade = CascadeType.REMOVE)
    private BasicFilesStudents cpfFile;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "rgFile", cascade = CascadeType.REMOVE)
    private BasicFilesStudents rgFile;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "proofOfAddress", cascade = CascadeType.REMOVE)
    private BasicFilesStudents proofOfAddressFile;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student", cascade = CascadeType.REMOVE)
    private List<Family> family;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "student", cascade = CascadeType.REMOVE)
    private FixedHealth fixedHealth;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "student", cascade = CascadeType.REMOVE)
    private FinancialResponsible financialResponsible;
}
