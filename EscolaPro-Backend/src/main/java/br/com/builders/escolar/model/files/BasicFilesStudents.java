package br.com.builders.escolar.model.files;

import br.com.builders.escolar.model.Person;
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
public class BasicFilesStudents {

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

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "cpfFile")
    private Student cpfFile;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "rgFile")
    private Student rgFile;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "proofOfAddress")
    private Student proofOfAddress;

}
