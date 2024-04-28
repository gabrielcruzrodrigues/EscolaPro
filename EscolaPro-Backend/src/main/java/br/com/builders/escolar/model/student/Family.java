package br.com.builders.escolar.model.student;

import br.com.builders.escolar.model.Person;
import br.com.builders.escolar.model.enums.FamilyTypeEnum;
import br.com.builders.escolar.model.files.BasicFilesFamily;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
public class Family extends Person {

    @Column(length = 100)
    private String workAddress;

    @Column(length = 50)
    private String occupation;

    @Column
    @Enumerated(EnumType.STRING)
    private FamilyTypeEnum type;

    @OneToOne(mappedBy = "cpfFile", cascade = CascadeType.REMOVE)
    private BasicFilesFamily cpfFile;

    @OneToOne(mappedBy = "rgFile", cascade = CascadeType.REMOVE)
    private BasicFilesFamily rgFile;

    @OneToOne(mappedBy = "proofOfAddress", cascade = CascadeType.REMOVE)
    private BasicFilesFamily proofOfAddressFile;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "student_id")
    private Student student;

    public Family(Long id, @NotNull @NotBlank String name, @NotBlank String identity, @NotBlank String cpf, LocalDate dateOfBirth,
                  @NotBlank String nationality, @NotBlank String naturalness, @Email @NotBlank String email, @NotBlank String cep,
                  @NotBlank String address, @NotBlank String phone, @NotBlank String neighborhood, @NotBlank String numberHouse,
                  @NotBlank String city, @NotBlank String state, @NotBlank String country, LocalDateTime createdAt, boolean active,
                  String workAddress, String occupation, FamilyTypeEnum type, BasicFilesFamily cpfFile, BasicFilesFamily rgFile,
                  BasicFilesFamily proofOfAddressFile, Student student
    ) {
        super(id, name, identity, cpf, dateOfBirth, nationality, naturalness, email, cep, address, phone, neighborhood, numberHouse, city, state, country, createdAt, active);
        this.workAddress = workAddress;
        this.occupation = occupation;
        this.type = type;
        this.cpfFile = cpfFile;
        this.rgFile = rgFile;
        this.proofOfAddressFile = proofOfAddressFile;
        this.student = student;
    }
}
