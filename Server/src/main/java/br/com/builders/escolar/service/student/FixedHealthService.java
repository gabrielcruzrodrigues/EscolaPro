package br.com.builders.escolar.service.student;

import br.com.builders.escolar.model.student.FixedHealth;
import br.com.builders.escolar.model.student.Student;
import br.com.builders.escolar.repository.FixedHealthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FixedHealthService {

    private final FixedHealthRepository fixedHealthRepository;

    public void createFixedHealth(Student student) {

    }
}
