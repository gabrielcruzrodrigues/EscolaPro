package br.com.builders.escolar.service.student;

import br.com.builders.escolar.exception.customized.FamilyNotFoundException;
import br.com.builders.escolar.exception.customized.IntegritDataException;
import br.com.builders.escolar.exception.customized.StudentNotFoundException;
import br.com.builders.escolar.model.DTO.CreateFamilyStudentDTO;
import br.com.builders.escolar.model.DTO.UpdateFamilyDTO;
import br.com.builders.escolar.model.student.Family;
import br.com.builders.escolar.model.student.Student;
import br.com.builders.escolar.repository.FamilyRepository;
import br.com.builders.escolar.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FamilyService {

    private final FamilyRepository familyRepository;
    private final StudentRepository studentRepository;

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

    @Transactional
    public void createFamilyByStudent(CreateFamilyStudentDTO data) {
        Optional<Student> studentOPT = this.studentRepository.findById(data.studentId());
        if (studentOPT.isPresent()) {
            Student student = studentOPT.get();

            Family family = Family.builder()
                    .dateOfBirth(data.dateOfBirth())
                    .address(data.address())
                    .workAddress(data.workAddress())
                    .occupation(data.occupation())
                    .neighborhood(data.neighborhood())
                    .city(data.city())
                    .phone(data.phone())
                    .state(data.state())
                    .cep(data.cep())
                    .createdAt(LocalDateTime.now())
                    .active(true)
                    .type(data.type())
                    .student(student)
                    .build();

            this.familyRepository.save(family);
        } else {
            throw new StudentNotFoundException();
        }
    }

    public Family findFamilyById(Long id) {
        Optional<Family> family = this.familyRepository.findById(id);
        return family.orElseThrow(FamilyNotFoundException::new);
    }

    public List<Family> findAllFamilyByStudent(Long idStudent) {
        return this.familyRepository.findAllByStudentId(idStudent);
    }

    @Transactional
    public void updateFamily(UpdateFamilyDTO data) {
        Family family = this.findFamilyById(data.id());

        family.setDateOfBirth(data.dateOfBirth());
        family.setAddress(data.address());
        family.setWorkAddress(data.workAddress());
        family.setOccupation(data.occupation());
        family.setNeighborhood(data.neighborhood());
        family.setCity(data.city());
        family.setPhone(data.phone());
        family.setState(data.state());
        family.setCep(data.cep());
        family.setType(data.type());

        this.familyRepository.save(family);
    }

    public void disabledFamily(Long familyId, Long studentId) {
        List<Family> familyCheck = this.findAllFamilyByStudent(studentId);
        if (familyCheck.size() > 1) {
            Family family = this.findFamilyById(familyId);
            family.setActive(false);
            this.familyRepository.save(family);
        } else {
            throw new IntegritDataException("The student cannot be without a family member");
        }
    }
}
