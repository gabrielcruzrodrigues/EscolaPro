package br.com.builders.escolar.model.student;

import br.com.builders.escolar.model.Person;
import br.com.builders.escolar.model.enums.SexEnum;
import br.com.builders.escolar.model.enums.ShiftEnum;
import br.com.builders.escolar.model.enums.SituationsStudentEnum;
import br.com.builders.escolar.model.files.BasicFilesStudents;
import br.com.builders.escolar.model.files.ImagesStudents;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@NoArgsConstructor
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

    public Student(Long id, @NotNull @NotBlank String name, @NotBlank String identity, @NotBlank String cpf, LocalDate dateOfBirth,
                   @NotBlank String nationality, @NotBlank String naturalness, @Email @NotBlank String email, @NotBlank String cep,
                   @NotBlank String address, @NotBlank String phone, @NotBlank String neighborhood, @NotBlank String numberHouse,
                   @NotBlank String city, @NotBlank String state, @NotBlank String country, LocalDateTime createdAt, boolean active,
                   SexEnum sex, SituationsStudentEnum situation, String emailPersonResponsible, String responsible, String father,
                   String mother, ImagesStudents imageProfile, BasicFilesStudents cpfFile, BasicFilesStudents rgFile,
                   BasicFilesStudents proofOfAddressFile, List<Family> family, FixedHealth fixedHealth, FinancialResponsible financialResponsible) {
        super(id, name, identity, cpf, dateOfBirth, nationality, naturalness, email, cep, address, phone, neighborhood, numberHouse, city, state, country, createdAt, active);
        this.sex = sex;
        this.situation = situation;
        this.emailPersonResponsible = emailPersonResponsible;
        this.responsible = responsible;
        this.father = father;
        this.mother = mother;
        this.imageProfile = imageProfile;
        this.cpfFile = cpfFile;
        this.rgFile = rgFile;
        this.proofOfAddressFile = proofOfAddressFile;
        this.family = family;
        this.fixedHealth = fixedHealth;
        this.financialResponsible = financialResponsible;
    }
}
