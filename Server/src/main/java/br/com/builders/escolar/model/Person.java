package br.com.builders.escolar.model;

import br.com.builders.escolar.model.enums.RoleEnum;
import br.com.builders.escolar.model.enums.SexEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    @NotNull
    @NotBlank
    private String name;

//    @Column(nullable = false)
//    private LocalDateTime entryDate;

    @Column(nullable = false, length = 10)
    @NotBlank
    private String identity;

    @Column(nullable = false, length = 11)
    @NotBlank
    private String cpf;

    @Column(nullable = false)
    private Date dateOfBirth;

    @Column(nullable = false)
    @NotBlank
    private String nationality;

    @Column(nullable = false)
    @NotBlank
    private String naturalness;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SexEnum sex;

    @Email
    @Column(nullable = false, length = 100)
    @NotBlank
    private String email;

    @Column(nullable = false, length = 8)
    @NotBlank
    private String cep;

    @Column(nullable = false, length = 100)
    @NotBlank
    private String address;

    @Column(nullable = false, length = 11)
    @NotBlank
    private String phone;

    @Column(nullable = false, length = 100)
    @NotBlank
    private String neighborhood;

    @Column(nullable = false, length = 50)
    @NotBlank
    private String city;

    @Column(nullable = false, length = 50)
    @NotBlank
    private String state;

    @Column(nullable = false, length = 50)
    @NotBlank
    private String country;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @Column
    private LocalDateTime createdAt;

    @Column
    private boolean active;
}
