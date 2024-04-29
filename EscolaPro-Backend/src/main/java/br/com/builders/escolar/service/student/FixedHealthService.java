package br.com.builders.escolar.service.student;

import br.com.builders.escolar.exception.customized.FixedHealthNotFoundException;
import br.com.builders.escolar.exception.customized.StudentNotFoundException;
import br.com.builders.escolar.model.DTO.CreateFixedHealthDTO;
import br.com.builders.escolar.model.DTO.UpdateFixedHealthDTO;
import br.com.builders.escolar.model.student.FixedHealth;
import br.com.builders.escolar.model.student.Student;
import br.com.builders.escolar.repository.FixedHealthRepository;
import br.com.builders.escolar.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FixedHealthService {

    private final FixedHealthRepository fixedHealthRepository;
    private final StudentRepository studentRepository;

    @Transactional
    public void createFixedHealthByStudent(CreateFixedHealthDTO data) {
        Optional<Student> studentOPT = this.studentRepository.findById(data.studentId());
        if (studentOPT.isPresent()) {
            Student student = studentOPT.get();

            FixedHealth fixedHealth = FixedHealth.builder()
                    .allergies(data.allergies())
                    .healthInsurance(data.healthInsurance())
                    .bloodGroup(data.bloodGroup())
                    .medicalClinic(data.medicalClinic())
                    .quantityBrothers(data.quantityBrothers())
                    .toGoOutAuthorization(data.toGoOutAuthorization())
                    .active(true)
                    .createdAt(LocalDateTime.now())
                    .student(student)
                    .build();

            this.fixedHealthRepository.save(fixedHealth);
        } else {
            throw new StudentNotFoundException();
        }
    }

    public FixedHealth findFixedHealthById(Long id) {
        Optional<FixedHealth> fixedHealth = this.fixedHealthRepository.findById(id);
        return fixedHealth.orElseThrow(FixedHealthNotFoundException::new);
    }

//    public List<FixedHealth> findAllFixedHealthByStudent(Long idStudent) {
//        return this.fixedHealthRepository.findAllByStudentId(idStudent);
//    }

    public void updateFixedHealth(UpdateFixedHealthDTO data) {
        FixedHealth fixedHealth = this.findFixedHealthById(data.id());
        fixedHealth.setAllergies(data.allergies());
        fixedHealth.setHealthInsurance(data.healthInsurance());
        fixedHealth.setBloodGroup(data.bloodGroup());
        fixedHealth.setMedicalClinic(data.medicalClinic());
        fixedHealth.setQuantityBrothers(data.quantityBrothers());
        fixedHealth.setToGoOutAuthorization(data.toGoOutAuthorization());

        this.fixedHealthRepository.save(fixedHealth);
    }
}
