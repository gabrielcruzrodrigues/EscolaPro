package br.com.builders.escolar.controller;

import br.com.builders.escolar.model.DTO.StudentCreateDataDTO;
import br.com.builders.escolar.model.DTO.StudentUpdateDataDTO;
import br.com.builders.escolar.model.enums.SituationsStudentEnum;
import br.com.builders.escolar.model.student.Student;
import br.com.builders.escolar.security.accessInterfaces.FinancialAccess;
import br.com.builders.escolar.security.accessInterfaces.PedagogicalAccess;
import br.com.builders.escolar.service.student.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

//    @PedagogicalAccess
    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody @Valid StudentCreateDataDTO request) {
        if (request.situation() == SituationsStudentEnum.MATRICULADO) {
            this.studentService.createStudentMATRICULADO(request);
        } else {
            this.studentService.createStudentPENDENTE(request);
        }
        return new ResponseEntity<>("Student created", HttpStatus.CREATED);
    }

    @PedagogicalAccess
    @GetMapping(path = "/search/{id}")
    public ResponseEntity<Student> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(this.studentService.findById(id));
    }

    @FinancialAccess
    @GetMapping
    public ResponseEntity<List<Student>> findAll() {
        return ResponseEntity.ok().body(this.studentService.findAll());
    }

    @PedagogicalAccess
    @GetMapping(path = "/active")
    public ResponseEntity<List<Student>> findAllActiveStudents() {
        return ResponseEntity.ok().body(this.studentService.findAllActiveStudents());
    }

    @PedagogicalAccess
    @GetMapping(path = "/active/matriculado")
    public ResponseEntity<List<Student>> listStudentsMATRICULADO() {
        return ResponseEntity.ok().body(this.studentService.listStudentsWithSituationMATRICULADO());
    }

    @PedagogicalAccess
    @GetMapping(path = "/active/pendente")
    public ResponseEntity<List<Student>> listStudentsPENDENTE() {
        return ResponseEntity.ok().body(this.studentService.listStudentsWithSituationPENDENTE());
    }

    @PedagogicalAccess
    @PutMapping
    public ResponseEntity<?> updateStudent(@RequestBody StudentUpdateDataDTO request) {
        this.studentService.updateStudent(request);
        return new ResponseEntity<>("Student updated", HttpStatus.NO_CONTENT);
    }

    @PedagogicalAccess
    @DeleteMapping(path = "/disable/{id}")
    public ResponseEntity<?> disableStudent(@PathVariable Long id) {
        this.studentService.disableStudent(id);
        return new ResponseEntity<>("Student disabled", HttpStatus.NO_CONTENT);
    }

    @PedagogicalAccess
    @GetMapping(path = "/search/name/{name}")
    public ResponseEntity<List<Student>> searchStudentByName(@PathVariable String name) {
        return ResponseEntity.ok().body(this.studentService.searchStudentByName(name));
    }

    @PedagogicalAccess
    @PutMapping(path = "/complete/registration/{id}")
    public ResponseEntity<?> completeRegistration(@PathVariable Long id) {
        this.studentService.completeRegistrationOfTheStudentPENDENTE(id);
        return ResponseEntity.noContent().build();
    }
}
