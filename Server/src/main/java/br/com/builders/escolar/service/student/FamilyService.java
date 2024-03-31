package br.com.builders.escolar.service.student;

import br.com.builders.escolar.model.student.Family;
import br.com.builders.escolar.model.student.Student;
import br.com.builders.escolar.repository.FamilyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FamilyService {

    private final FamilyRepository familyRepository;

    @Transactional
    public void createBasicFamily(Student student) {
        Family family = modelingBasicFamily(student);
        this.familyRepository.save(family);
    }

    private Family modelingBasicFamily(Student student) {
        return Family.builder()
                .student(student)
                .build();
    }
}
