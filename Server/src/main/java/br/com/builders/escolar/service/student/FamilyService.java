package br.com.builders.escolar.service.student;

import br.com.builders.escolar.model.student.Family;
import br.com.builders.escolar.model.student.Student;
import br.com.builders.escolar.repository.FamilyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FamilyService {

    private final FamilyRepository familyRepository;

    public void createFamily(Student student) {

    }
}
