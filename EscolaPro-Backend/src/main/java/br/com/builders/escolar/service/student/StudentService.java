package br.com.builders.escolar.service.student;

import br.com.builders.escolar.exception.customized.UserNotFoundException;
import br.com.builders.escolar.model.DTO.CreateFamilyStudentDTO;
import br.com.builders.escolar.model.DTO.FullStudentWith2FamilyDTO;
import br.com.builders.escolar.model.DTO.StudentCreateDataDTO;
import br.com.builders.escolar.model.enums.FileTypeEnum;
import br.com.builders.escolar.model.enums.SituationsStudentEnum;
import br.com.builders.escolar.model.student.Student;
import br.com.builders.escolar.repository.StudentRepository;
import br.com.builders.escolar.service.files.FilesStudentService;
import br.com.builders.escolar.utils.VerifyIfIsNotNullOfEmpty;
import br.com.builders.escolar.utils.converters.FullStudentByFamily;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    @Value("${files-students-path}")
    private String studentPath;
    @Value("${images-students-path}")
    private String imageStudentPath;

    @Value("${files-financialResponsible-path}")
    private String financialResponsiblePath;

    @Value("${files-family-path}")
    private String filesFamilyPath;

    private final StudentRepository studentRepository;
    private final FilesStudentService filesStudentService;
    private final FamilyService familyService;
    private final FixedHealthService fixedHealthService;

    @Transactional
    public Student createStudentMATRICULADO(StudentCreateDataDTO request) {
        Student studentForSave = modelingNewStudentForSave(request, SituationsStudentEnum.MATRICULADO);
        Student student = studentRepository.save(studentForSave);
        this.saveFiles(request, student);
        return student;
    }

    private void saveFiles(StudentCreateDataDTO request, Student student) {
        if (request.imageProfile() != null) {
            this.filesStudentService.saveFile(request.imageProfile(), student, FileTypeEnum.IMAGE);
        }
        if(request.rgFile() != null) {
            this.filesStudentService.saveFile(request.rgFile(), student, FileTypeEnum.RG);
        }
        if(request.cpfFile() != null) {
            this.filesStudentService.saveFile(request.cpfFile(), student, FileTypeEnum.CPF);
        }
        if(request.proofOfAddress() != null) {
            this.filesStudentService.saveFile(request.proofOfAddress(), student, FileTypeEnum.PROOF_OF_ADDRESS);
        }
    }

    @Transactional
    public Student createStudentPENDENTE(StudentCreateDataDTO request) {
        Student studentForSave = modelingNewStudentForSave(request, SituationsStudentEnum.PENDENTE);
        Student student = studentRepository.save(studentForSave);
        return student;

//        if (request.imageProfile() != null) {
//            this.filesStudentService.saveImage(request.imageProfile(), student);
//        }
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
        student.setNumberHouse(request.numberHouse());
        student.setCity(request.city());
        student.setState(request.state());
        student.setCountry(request.country());
        student.setCreatedAt(LocalDateTime.now());
        student.setEmailPersonResponsible(request.emailPersonResponsible());
        student.setResponsible(request.responsible());
        student.setFather(request.father());
        student.setMother(request.mother());

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

    public Page<Student> findAllActiveStudents(boolean active, Pageable pageable) {
        return this.studentRepository.findAllActiveStudents(active, pageable);
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
    public void updateStudent(FullStudentWith2FamilyDTO request) {
        Student student = this.findById(request.id());

        //student
        student.setName(request.name());
        student.setCpf(request.cpf());
        student.setIdentity(request.identity());
        student.setDateOfBirth(request.dateOfBirth());
        student.setPhone(request.phone());
        student.setEmail(request.email());
        student.setCep(request.cep());
        student.setAddress(request.address());
        student.setCity(request.city());
        student.setNeighborhood(request.neighborhood());
        student.setNumberHouse(request.numberHouse());
        student.setNationality(request.nationality());
        student.setSex(request.sex());
        student.setState(request.state());
        student.setCountry(request.country());
        student.setResponsible(request.responsible());
        student.setEmailPersonResponsible(request.emailPersonResponsible());
        student.setFather(request.father());
        student.setMother(request.mother());
        student.setNaturalness(request.naturalness());
//        student.getFinancialResponsible();
//        updateFiles()
        //family 1
        student.getFamily().get(0).setName(request.familyName());
        student.getFamily().get(0).setCpf(request.familyCpf());
        student.getFamily().get(0).setIdentity(request.familyIdentity());
        student.getFamily().get(0).setEmail(request.familyEmail());
        student.getFamily().get(0).setDateOfBirth(request.familyDateOfBirth());
        student.getFamily().get(0).setOccupation(request.familyOccupation());
        student.getFamily().get(0).setType(request.familyType());
        student.getFamily().get(0).setCep(request.familyCep());
        student.getFamily().get(0).setAddress(request.familyAddress());
        student.getFamily().get(0).setNumberHouse(request.familyNumberHouse());
        student.getFamily().get(0).setNeighborhood(request.familyNeighborhood());
        student.getFamily().get(0).setNationality(request.familyNationality());
        student.getFamily().get(0).setCity(request.familyCity());
        student.getFamily().get(0).setWorkAddress(request.familyWorkAddress());
        student.getFamily().get(0).setPhone(request.familyPhone());
        student.getFamily().get(0).setState(request.familyState());
        student.getFamily().get(0).setCountry(request.familyCountry());
        student.getFamily().get(0).setNaturalness(request.familyNaturalness());
        //        updateFiles()
        // financial responsible
        student.getFinancialResponsible().setName(request.familyName());
        student.getFinancialResponsible().setCpf(request.familyCpf());
        student.getFinancialResponsible().setIdentity(request.familyIdentity());
        student.getFinancialResponsible().setEmail(request.familyEmail());
        student.getFinancialResponsible().setDateOfBirth(request.familyDateOfBirth());
        student.getFinancialResponsible().setOccupation(request.familyOccupation());
        student.getFinancialResponsible().setType(request.familyType());
        student.getFinancialResponsible().setCep(request.familyCep());
        student.getFinancialResponsible().setAddress(request.familyAddress());
        student.getFinancialResponsible().setNumberHouse(request.familyNumberHouse());
        student.getFinancialResponsible().setNeighborhood(request.familyNeighborhood());
        student.getFinancialResponsible().setNationality(request.familyNationality());
        student.getFinancialResponsible().setCity(request.familyCity());
        student.getFinancialResponsible().setWorkAddress(request.familyWorkAddress());
        student.getFinancialResponsible().setPhone(request.familyPhone());
        student.getFinancialResponsible().setState(request.familyState());
        student.getFinancialResponsible().setCountry(request.familyCountry());
        student.getFinancialResponsible().setNaturalness(request.familyNaturalness());
        //        updateFiles()
        //fixedHealth
        student.getFixedHealth().setAllergies(request.allergies());
        student.getFixedHealth().setHealthInsurance(request.healthInsurance());
        student.getFixedHealth().setBloodGroup(request.bloodGroup());
        student.getFixedHealth().setMedicalClinic(request.medicalClinic());
        student.getFixedHealth().setQuantityBrothers(request.quantityBrothers());
        student.getFixedHealth().setToGoOutAuthorization(request.toGoOutAuthorization());

        if (student.getFamily().size() == 2) {
            //family 2
            student.getFamily().get(1).setName(request.familyName());
            student.getFamily().get(1).setCpf(request.familyCpf());
            student.getFamily().get(1).setIdentity(request.familyIdentity());
            student.getFamily().get(1).setEmail(request.familyEmail());
            student.getFamily().get(1).setDateOfBirth(request.familyDateOfBirth());
            student.getFamily().get(1).setOccupation(request.familyOccupation());
            student.getFamily().get(1).setType(request.familyType());
            student.getFamily().get(1).setCep(request.familyCep());
            student.getFamily().get(1).setAddress(request.familyAddress());
            student.getFamily().get(1).setNumberHouse(request.familyNumberHouse());
            student.getFamily().get(1).setNeighborhood(request.familyNeighborhood());
            student.getFamily().get(1).setNationality(request.familyNationality());
            student.getFamily().get(1).setCity(request.familyCity());
            student.getFamily().get(1).setWorkAddress(request.familyWorkAddress());
            student.getFamily().get(1).setPhone(request.familyPhone());
            student.getFamily().get(1).setState(request.familyState());
            student.getFamily().get(1).setCountry(request.familyCountry());
            student.getFamily().get(1).setNaturalness(request.familyNaturalness());
            //        updateFiles()
        }
//        if (areAllFieldsFilled(request)) {
//            CreateFamilyStudentDTO family = FullStudentByFamily.convertToCreateFamilyStudentDTO2(request);
//            familyService.createFamilyByStudent(family);
//        }

        this.studentRepository.save(student);
    }

    public void disableStudent(Long id) {
        Student student = this.findById(id);
        student.setActive(false);
        this.studentRepository.save(student);
    }

    @Transactional
    public void deleteStudent(Long id) {
        Student student = this.findById(id);
        this.studentRepository.delete(student);
    }

    @Transactional
    public void completeRegistrationOfTheStudentPENDENTE(Long id) {
        Student student = this.findById(id);

        student.setActive(true);
        student.setSituation(SituationsStudentEnum.MATRICULADO);
    }

    public boolean verifyIfQuantityFamilyStudentIsGreaterThanOne(long id) {
        Student student = this.findById(id);
        return student.getFamily().size() > 1;
    }

    public boolean areAllFieldsFilled(FullStudentWith2FamilyDTO request) {
        return VerifyIfIsNotNullOfEmpty.isNotNullOrEmpty(request.familyName()) &&
                VerifyIfIsNotNullOfEmpty.isNotNullOrEmpty(request.familyCpf()) &&
                VerifyIfIsNotNullOfEmpty.isNotNullOrEmpty(request.familyIdentity()) &&
                VerifyIfIsNotNullOfEmpty.isNotNullOrEmpty(request.familyEmail()) &&
                VerifyIfIsNotNullOfEmpty.isNotNullOrEmpty(request.familyDateOfBirth().toString()) &&
                VerifyIfIsNotNullOfEmpty.isNotNullOrEmpty(request.familyOccupation()) &&
                VerifyIfIsNotNullOfEmpty.isNotNullOrEmpty(request.familyType().toString()) &&
                VerifyIfIsNotNullOfEmpty.isNotNullOrEmpty(request.familyCep()) &&
                VerifyIfIsNotNullOfEmpty.isNotNullOrEmpty(request.familyAddress()) &&
                VerifyIfIsNotNullOfEmpty.isNotNullOrEmpty(request.familyNumberHouse()) &&
                VerifyIfIsNotNullOfEmpty.isNotNullOrEmpty(request.familyNeighborhood()) &&
                VerifyIfIsNotNullOfEmpty.isNotNullOrEmpty(request.familyNationality()) &&
                VerifyIfIsNotNullOfEmpty.isNotNullOrEmpty(request.familyCity()) &&
                VerifyIfIsNotNullOfEmpty.isNotNullOrEmpty(request.familyWorkAddress()) &&
                VerifyIfIsNotNullOfEmpty.isNotNullOrEmpty(request.familyPhone()) &&
                VerifyIfIsNotNullOfEmpty.isNotNullOrEmpty(request.familyState()) &&
                VerifyIfIsNotNullOfEmpty.isNotNullOrEmpty(request.familyCountry()) &&
                VerifyIfIsNotNullOfEmpty.isNotNullOrEmpty(request.familyNaturalness());
    }
}
