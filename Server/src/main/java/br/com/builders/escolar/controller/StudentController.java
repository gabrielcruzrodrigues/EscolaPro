package br.com.builders.escolar.controller;

import br.com.builders.escolar.model.DTO.CreateFamilyStudentDTO;
import br.com.builders.escolar.model.DTO.StudentCreateDataDTO;
import br.com.builders.escolar.model.DTO.StudentUpdateDataDTO;
import br.com.builders.escolar.model.DTO.UpdateFamilyDTO;
import br.com.builders.escolar.model.enums.SituationsStudentEnum;
import br.com.builders.escolar.model.student.Family;
import br.com.builders.escolar.model.student.Student;
import br.com.builders.escolar.security.accessInterfaces.FinancialAccess;
import br.com.builders.escolar.security.accessInterfaces.PedagogicalAccess;
import br.com.builders.escolar.service.student.FamilyService;
import br.com.builders.escolar.service.student.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final FamilyService familyService;

//    @PedagogicalAccess
    @PostMapping
    public ResponseEntity<Object> createStudent(@RequestBody @Valid StudentCreateDataDTO request) {
        if (request.situation() == SituationsStudentEnum.MATRICULADO) {
            this.studentService.createStudentMATRICULADO(request);
        } else {
            this.studentService.createStudentPENDENTE(request);
        }

        Map<String, String> response = new HashMap<>();
        response.put("message", "user created!");

//        return ResponseEntity.ok().body(response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
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

    //======================== family =================================
    @PostMapping(path = "/family")
    public ResponseEntity<?> createFamily(@RequestBody CreateFamilyStudentDTO request) {
        this.familyService.createFamilyByStudent(request);
        return new ResponseEntity<>("Family created.", HttpStatus.CREATED);
    }

    @GetMapping(path = "/family/{id}")
    public ResponseEntity<Family> findFamilyById(@PathVariable Long id) {
        return ResponseEntity.ok().body(this.familyService.findFamilyById(id));
    }

    @GetMapping(path = "/family/all/{id}")
    public ResponseEntity<List<Family>> findAllFamilyByStudent(@PathVariable Long id) {
        return ResponseEntity.ok().body(this.familyService.findByAllFamilyByStudent(id));
    }

    @PutMapping(path = "/family")
    public ResponseEntity<?> updateFamily(@RequestBody UpdateFamilyDTO request) {
        this.familyService.updateFamily(request);
        return ResponseEntity.ok().body("Family updated.");
    }

    @DeleteMapping(path = "/family/{id}")
    public ResponseEntity<?> disabledFamily(@PathVariable Long id) {
        this.familyService.disabledFamily(id);
        return ResponseEntity.ok().body("family disabled");
    }
}
