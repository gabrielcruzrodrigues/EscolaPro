package br.com.builders.escolar.service.student;

import br.com.builders.escolar.model.student.FixedHealth;
import br.com.builders.escolar.model.student.Student;
import br.com.builders.escolar.repository.FixedHealthRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FixedHealthService {

    private final FixedHealthRepository fixedHealthRepository;

    @Transactional
    public void createBasicFixedHealth(Student student) {
        FixedHealth fixedHealth = modelingBasicFixedHealth(student);
        this.fixedHealthRepository.save(fixedHealth);
    }

    private FixedHealth modelingBasicFixedHealth(Student student) {
        return FixedHealth.builder()
                .student(student)
                .build();
    }
}
