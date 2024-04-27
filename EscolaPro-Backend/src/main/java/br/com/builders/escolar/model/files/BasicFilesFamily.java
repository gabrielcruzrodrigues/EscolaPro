package br.com.builders.escolar.model.files;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.builders.escolar.model.enums.FileTypeEnum;
import br.com.builders.escolar.model.student.Family;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
public class BasicFilesFamily {

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
     private Family cpfFile;
 
     @JsonIgnore
     @OneToOne
     @JoinColumn(name = "rgFile")
     private Family rgFile;
 
     @JsonIgnore
     @OneToOne
     @JoinColumn(name = "proofOfAddress")
     private Family proofOfAddress;
}