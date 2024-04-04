package br.com.builders.escolar.service.student;

import br.com.builders.escolar.exception.customized.UserNotFoundException;
import br.com.builders.escolar.model.DTO.StudentCreateDataDTO;
import br.com.builders.escolar.model.DTO.StudentUpdateDataDTO;
import br.com.builders.escolar.model.enums.SituationsStudentEnum;
import br.com.builders.escolar.model.student.Student;
import br.com.builders.escolar.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final FilesStudentService filesStudentService;
    private final FamilyService familyService;
    private final FixedHealthService fixedHealthService;

    @Transactional
    public void createStudentMATRICULADO(StudentCreateDataDTO request) {
        Student studentForSave = modelingNewStudentForSave(request, SituationsStudentEnum.MATRICULADO);
        Student student = studentRepository.save(studentForSave);

        if (request.imageProfile() != null) {
            this.filesStudentService.saveImage(request.imageProfile(), student);
        }
        this.familyService.createBasicFamily(student);
        this.fixedHealthService.createBasicFixedHealth(student);
    }

    @Transactional
    public void createStudentPENDENTE(StudentCreateDataDTO request) {
        Student studentForSave = modelingNewStudentForSave(request, SituationsStudentEnum.PENDENTE);
        Student student = studentRepository.save(studentForSave);

        if (request.imageProfile() != null) {
            this.filesStudentService.saveImage(request.imageProfile(), student);
        }
    }

    private Student modelingNewStudentForSave(StudentCreateDataDTO request, SituationsStudentEnum situationStudent) {
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
        student.setCreatedAt(LocalDateTime.now());
        student.setEmailPersonResponsible(request.emailPersonResponsible());
        student.setResponsible(request.responsible());
        student.setFather(request.father());
        student.setMother(request.mother());
        student.setShifts(request.shifts());

        if (situationStudent.equals(SituationsStudentEnum.MATRICULADO)) {
            student.setSituation(SituationsStudentEnum.MATRICULADO);
            student.setActive(true);
        } else {
            student.setSituation(SituationsStudentEnum.PENDENTE);
            student.setActive(false);
        }

        return student;
    }

    public Student findById(Long id) {
        return this.studentRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public List<Student> findAll() {
        return this.studentRepository.findAll();
    }

    public List<Student> findAllActiveStudents() {
        return this.studentRepository.findAllActiveStudents(true);
    }

    public List<Student> listStudentsWithSituationMATRICULADO() {
        return this.studentRepository.listStudentBySituation(SituationsStudentEnum.MATRICULADO);
    }

    public List<Student> listStudentsWithSituationPENDENTE() {
        return this.studentRepository.listStudentBySituation(SituationsStudentEnum.PENDENTE);
    }

    public List<Student> searchStudentByName(String name) {
        return this.studentRepository.searchStudentByName(name);
    }

    @Transactional
    public void updateStudent(StudentUpdateDataDTO request) {
        Student student = this.findById(request.id());
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
        student.setActive(true);
        student.setEmailPersonResponsible(request.emailPersonResponsible());
        student.setResponsible(request.responsible());
        student.setFather(request.father());
        student.setMother(request.mother());
        student.setShifts(request.shifts());
        student.setSituation(SituationsStudentEnum.MATRICULADO);
        this.studentRepository.save(student);
    }

    public void disableStudent(Long id) {
        Student student = this.findById(id);
        student.setActive(false);
        this.studentRepository.save(student);
    }

    @Transactional
    public void completeRegistrationOfTheStudentPENDENTE(Long id) {
        Student student = this.findById(id);

        student.setActive(true);
        student.setSituation(SituationsStudentEnum.MATRICULADO);

        this.familyService.createBasicFamily(student);
        this.fixedHealthService.createBasicFixedHealth(student);
    }
}
