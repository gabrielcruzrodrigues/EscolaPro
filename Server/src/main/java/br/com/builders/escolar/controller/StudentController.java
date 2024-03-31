package br.com.builders.escolar.controller;

import br.com.builders.escolar.model.DTO.StudentCreateData;
import br.com.builders.escolar.model.student.Student;
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

    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody @Valid StudentCreateData request) {
        this.studentService.create(request);
        return new ResponseEntity<>("Student created", HttpStatus.CREATED);
    }

    @GetMapping(path = "/search/{id}")
    public ResponseEntity<Student> findById(@RequestParam Long id) {
        return ResponseEntity.ok().body(this.studentService.findById(id));
    }
}
