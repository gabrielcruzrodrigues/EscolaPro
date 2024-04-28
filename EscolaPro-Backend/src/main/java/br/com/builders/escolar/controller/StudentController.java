package br.com.builders.escolar.controller;

import br.com.builders.escolar.model.DTO.*;
import br.com.builders.escolar.model.enums.SituationsStudentEnum;
import br.com.builders.escolar.model.student.Family;
import br.com.builders.escolar.model.student.FinancialResponsible;
import br.com.builders.escolar.model.student.FixedHealth;
import br.com.builders.escolar.model.student.Student;
import br.com.builders.escolar.security.accessInterfaces.FinancialAccess;
import br.com.builders.escolar.security.accessInterfaces.PedagogicalAccess;
import br.com.builders.escolar.service.student.FamilyService;
import br.com.builders.escolar.service.student.FinancialResponsibleService;
import br.com.builders.escolar.service.student.FixedHealthService;
import br.com.builders.escolar.service.student.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final FamilyService familyService;
    private final FixedHealthService fixedHealthService;
    private final FinancialResponsibleService financialResponsibleService;

//    @PedagogicalAccess
    @PostMapping
    public ResponseEntity<Student> createStudent(@ModelAttribute @Valid StudentCreateDataDTO request) {
        Student student = new Student();

        if (request.situation() == SituationsStudentEnum.MATRICULADO) {
            student = this.studentService.createStudentMATRICULADO(request);
        } else {
//            student = this.studentService.createStudentPENDENTE(request);
        }
        return new ResponseEntity<>(student, HttpStatus.CREATED);
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
    @GetMapping(path = "/{active}")
    public ResponseEntity<Page<Student>> findAllActiveStudents(Pageable pageable, @PathVariable boolean active) {
        return ResponseEntity.ok().body(this.studentService.findAllStudents(active, pageable));
    }

//    @PedagogicalAccess
//    @GetMapping(path = "/active/matriculado")
//    public ResponseEntity<List<Student>> listStudentsMATRICULADO() {
//        return ResponseEntity.ok().body(this.studentService.listStudentsWithSituationMATRICULADO());
//    }
//
//    @PedagogicalAccess
//    @GetMapping(path = "/active/pendente")
//    public ResponseEntity<List<Student>> listStudentsPENDENTE() {
//        return ResponseEntity.ok().body(this.studentService.listStudentsWithSituationPENDENTE());
//    }

    @PedagogicalAccess
    @PutMapping
    public ResponseEntity<?> updateStudent(@RequestBody FullStudentWith2FamilyDTO request) {
        this.studentService.updateStudent(request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PedagogicalAccess
    @DeleteMapping(path = "/disable/{id}")
    public ResponseEntity<?> disableStudent(@PathVariable Long id) {
        this.studentService.disableStudent(id);
        return new ResponseEntity<>("Student disabled", HttpStatus.NO_CONTENT);
    }

    @PedagogicalAccess
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Object> deleteStudent(@PathVariable Long id) {
        this.studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @PedagogicalAccess
    @GetMapping(path = "/search/name/{name}")
    public ResponseEntity<List<Student>> searchStudentByName(@PathVariable String name) {
        return ResponseEntity.ok().body(this.studentService.searchStudentByName(name));
    }

    @PedagogicalAccess
    @PutMapping(path = "/complete/registration/{id}")
    public ResponseEntity<?> completeRegistration(@PathVariable Long id) {
        this.studentService.completeRegistrationOfStudentPENDENTE(id);
        return ResponseEntity.noContent().build();
    }

    //======================== family =================================
    @PostMapping(path = "/family")
    public ResponseEntity<Object> createFamily(@ModelAttribute @Valid CreateFamilyStudentDTO request) {
        this.familyService.createFamilyByStudent(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(path = "/family/{id}")
    public ResponseEntity<Family> findFamilyById(@PathVariable Long id) {
        return ResponseEntity.ok().body(this.familyService.findFamilyById(id));
    }

    @GetMapping(path = "/family/all/{id}")
    public ResponseEntity<List<Family>> findAllFamilyByStudent(@PathVariable Long id) {
        return ResponseEntity.ok().body(this.familyService.findAllFamilyByStudent(id));
    }

    @PutMapping(path = "/family")
    public ResponseEntity<?> updateFamily(@RequestBody UpdateFamilyDTO request) {
        this.familyService.updateFamily(request);
        return ResponseEntity.ok().body("Family updated.");
    }

    @DeleteMapping(path = "/family/{familyId}/{studentId}")
    public ResponseEntity<?> disabledFamily(@PathVariable Long familyId, @PathVariable Long studentId) {
        this.familyService.disabledFamily(familyId, studentId);
        return ResponseEntity.ok().body("family disabled");
    }

    //======================== fixed health =================================

    @PostMapping(path = "/fixedhealth")
    public ResponseEntity<?> createFixedHealth(@RequestBody CreateFixedHealthDTO request) {
        this.fixedHealthService.createFixedHealthByStudent(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(path = "/fixedhealth/{id}")
    public ResponseEntity<FixedHealth> findFixedHealthById(@PathVariable Long id) {
        return ResponseEntity.ok().body(this.fixedHealthService.findFixedHealthById(id));
    }

    @GetMapping(path = "/fixedhealth/all/{id}")
    public ResponseEntity<List<FixedHealth>> findAllFixedHealthByStudent(@PathVariable Long id) {
        return ResponseEntity.ok().body(this.fixedHealthService.findAllFixedHealthByStudent(id));
    }

    @PutMapping(path = "/fixedhealth")
    public ResponseEntity<?> updateFixedHealth(@RequestBody UpdateFixedHealthDTO request) {
        this.fixedHealthService.updateFixedHealth(request);
        return ResponseEntity.ok().body("Fixed health updated.");
    }

    //======================== Financial Responsible =================================

    @PostMapping(path = "/financialresponsible")
    public ResponseEntity<Object> createFinancialResponsible(@ModelAttribute @Valid CreateFinancialResponsibleDTO request) {
        this.financialResponsibleService.createFinancialResponsible(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(path = "/financialresponsible/{id}")
    public ResponseEntity<FinancialResponsible> findFinancialResponsible(@PathVariable Long id) {
        return ResponseEntity.ok().body(this.financialResponsibleService.findFinancialResponsibleById(id));
    }

    @GetMapping(path = "/financialresponsible/all/{id}")
    public ResponseEntity<List<FinancialResponsible>> findAllFinancialResponsibleByStudent(@PathVariable Long id) {
        return ResponseEntity.ok().body(this.financialResponsibleService.findAllFinancialResponsibleByStudent(id));
    }

    @PutMapping(path = "/financialresponsible")
    public ResponseEntity<?> updateFinancialResponsible(@RequestBody UpdateFinancialResponsibleDTO request) {
        this.financialResponsibleService.updateFinancialResponsible(request);
        return ResponseEntity.ok().body("Fixed health updated.");
    }
}
