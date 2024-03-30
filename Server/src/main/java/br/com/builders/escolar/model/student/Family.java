package br.com.builders.escolar.model.student;

import br.com.builders.escolar.model.enums.FamilyTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Family {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date dateOfBirth;

    @Column(nullable = false, length = 100)
    @NotBlank
    private String address;

    @Column(nullable = false, length = 100)
    @NotBlank
    private String workAddress;

    @Column(nullable = false, length = 50)
    @NotBlank
    private String occupation;

    @Column(nullable = false, length = 50)
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
    private String cep;

    @Column
    private boolean active;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FamilyTypeEnum type;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "student_id")
    private Student student;
}
