package br.com.builders.escolar.model.student;

import br.com.builders.escolar.model.files.FilesStudent;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    @NotBlank
    private String name;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "module_requestModules",
                joinColumns = @JoinColumn(name = "module_fk"),
                inverseJoinColumns = @JoinColumn(name = "requestModules_fk"))
    private List<RequestModules> requestModules;
}
