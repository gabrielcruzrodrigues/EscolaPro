package br.com.builders.escolar.service.student;

import br.com.builders.escolar.exception.customized.UserNotFoundException;
import br.com.builders.escolar.model.DTO.StudentCreateData;
import br.com.builders.escolar.model.student.Student;
import br.com.builders.escolar.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final FilesStudentService filesStudentService;
    private final FamilyService familyService;
    private final FixedHealthService fixedHealthService;

    @Transactional
    public void create(StudentCreateData request) {
        Student studentForSave = modelingNewStudentForSave(request);
        Student student = studentRepository.save(studentForSave);

        if (request.imageProfile() != null) {
            this.filesStudentService.saveImage(request.imageProfile(), student);
        }
        this.familyService.createFamily(student);
        this.fixedHealthService.createFixedHealth(student);
    }

    private Student modelingNewStudentForSave(StudentCreateData request) {
        Student student = new Student();
        student.setName(request.name());
        student.setIdentity(request.identity());
        student.setCpf(request.cpf());
        student.setDateOfBirth(request.dateOfBirth());
        student.setNationality(request.nationality());
        student.setNaturalness(request.naturalness());
        student.setSex(request.sex());
        student.setEmail(request.email());
        student.setCep(request.cep());
        student.setAddress(request.address());
        student.setPhone(request.phone());
        student.setNeighborhood(request.neighborhood());
        student.setCity(request.city());
        student.setState(request.state());
        student.setCountry(request.country());
        student.setEmailPersonResponsible(request.emailPersonResponsible());
        student.setResponsible(request.responsible());
        student.setFather(request.father());
        student.setMother(request.mother());
        student.setShifts(request.shifts());
        return student;
    }

    public Student findById(Long id) {
        return this.studentRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }
}
