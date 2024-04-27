package br.com.builders.escolar.model.files;

import br.com.builders.escolar.model.enums.FileTypeEnum;
import br.com.builders.escolar.model.student.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ImagesStudents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String register;

    @Column
    @Enumerated(EnumType.STRING)
    private FileTypeEnum type;

    @Column(nullable = false)
    private String reference;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "student_id")
    private Student student;
}
