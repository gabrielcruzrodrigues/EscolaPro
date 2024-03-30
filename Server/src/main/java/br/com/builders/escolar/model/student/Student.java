package br.com.builders.escolar.model.student;

import br.com.builders.escolar.model.Person;
import br.com.builders.escolar.model.enums.ShiftEnum;
import br.com.builders.escolar.model.enums.SituationsStudentEnum;
import br.com.builders.escolar.model.files.FilesStudent;
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

    @OneToMany(mappedBy = "student")
    private List<Family> family;

    @OneToOne(mappedBy = "student")
    private FixedHealth fixedHealth;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private List<ShiftEnum> shifts;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SituationsStudentEnum situation;

    @OneToMany(mappedBy = "student")
    private List<FilesStudent> imageProfile;
}
