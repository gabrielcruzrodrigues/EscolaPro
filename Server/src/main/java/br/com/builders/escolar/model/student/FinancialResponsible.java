package br.com.builders.escolar.model.student;

import br.com.builders.escolar.model.enums.FamilyTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FinancialResponsible {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String name;

    @Column(length = 100, nullable = false)
    @Email
    private String email;

    @Column(nullable = false, length = 10)
    @NotBlank
    private String identity;

    @Column(nullable = false, length = 11)
    @NotBlank
    private String cpf;

    @Column
    private LocalDate dateOfBirth;

    @Column(length = 100)
    private String address;

    @Column(length = 100)
    private String workAddress;

    @Column(length = 50)
    private String occupation;

    @Column(length = 50)
    private String neighborhood;

    @Column(nullable = false, length = 10)
    @NotBlank
    private String numberHouse;

    @Column(length = 50)
    private String city;

    @Column(length = 12)
    private String phone;

    @Column(length = 50)
    private String state;

    @Column(length = 50)
    private String cep;

    @Column
    @Enumerated(EnumType.STRING)
    private FamilyTypeEnum type;

    @Column
    private LocalDateTime createdAt;

    @Column
    private boolean active;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "student_id")
    private Student student;
}
