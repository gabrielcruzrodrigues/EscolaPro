package br.com.builders.escolar.model.files;

import br.com.builders.escolar.model.enums.FileTypeEnum;
import br.com.builders.escolar.model.student.FinancialResponsible;
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
public class BasicFilesFinancialResponsible {

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
    private FinancialResponsible cpfFile;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "rgFile")
    private FinancialResponsible rgFile;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "proofOfAddress")
    private FinancialResponsible proofOfAddress;
}
