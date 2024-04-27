package br.com.builders.escolar.model;

import br.com.builders.escolar.model.enums.SexEnum;
import br.com.builders.escolar.model.files.BasicFilesStudents;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false, length = 100)
    @NotNull
    @NotBlank
    protected String name;

    @Column(nullable = false, length = 10)
    @NotBlank
    protected String identity;

    @Column(nullable = false, length = 11)
    @NotBlank
    protected String cpf;

    @Column(nullable = false)
    protected LocalDate dateOfBirth;

    @Column(nullable = false)
    @NotBlank
    protected String nationality;

    @Column(nullable = false)
    @NotBlank
    protected String naturalness;

    @Email
    @Column(nullable = false, length = 100)
    @NotBlank
    protected String email;

    @Column(nullable = false, length = 8)
    @NotBlank
    protected String cep;

    @Column(nullable = false, length = 100)
    @NotBlank
    protected String address;

    @Column(nullable = false, length = 11)
    @NotBlank
    protected String phone;

    @Column(nullable = false, length = 100)
    @NotBlank
    protected String neighborhood;

    @Column(nullable = false, length = 10)
    @NotBlank
    protected String numberHouse;

    @Column(nullable = false, length = 50)
    @NotBlank
    protected String city;

    @Column(nullable = false, length = 50)
    @NotBlank
    protected String state;

    @Column(nullable = false, length = 50)
    @NotBlank
    protected String country;

    @Column
    protected LocalDateTime createdAt;

    @Column
    protected boolean active;
}
