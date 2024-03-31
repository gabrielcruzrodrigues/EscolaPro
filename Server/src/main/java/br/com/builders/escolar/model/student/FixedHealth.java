package br.com.builders.escolar.model.student;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FixedHealth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private List<String> allergies;

    @Column(length = 50)
    private String healthInsurance;

    @Column(length = 50)
    private String bloodGroup;

    @Column(length = 50)
    private String medicalClinic;

    @Column(nullable = false)
    private Long quantityBrothers;

    @Column(nullable = false)
    private boolean toGoOutAuthorization;

    @Column(nullable = false)
    private boolean active;

    @Column
    private LocalDateTime createdAt;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "student_id")
    private Student student;
}
