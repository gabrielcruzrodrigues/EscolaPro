package br.com.builders.escolar.model.student;

import br.com.builders.escolar.model.enums.FamilyTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Family {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @Column(length = 50)
    private String city;

    @Column(length = 12)
    private String phone;

    @Column(length = 50)
    private String state;

    @Column(length = 50)
    private String cep;

    @Column
    private LocalDateTime createdAt;

    @Column
    private boolean active;

    @Column
    @Enumerated(EnumType.STRING)
    private FamilyTypeEnum type;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "student_id")
    private Student student;
}
