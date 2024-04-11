package br.com.builders.escolar.service.student;

import br.com.builders.escolar.exception.customized.FinancialResponsibleNotFoundException;
import br.com.builders.escolar.exception.customized.IntegritDataException;
import br.com.builders.escolar.model.DTO.CreateFinancialResponsibleDTO;
import br.com.builders.escolar.model.DTO.UpdateFinancialResponsibleDTO;
import br.com.builders.escolar.model.student.FinancialResponsible;
import br.com.builders.escolar.model.student.Student;
import br.com.builders.escolar.repository.FinancialResponsibleRepository;
import br.com.builders.escolar.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FinancialResponsibleService {

    private final FinancialResponsibleRepository financialResponsibleRepository;
    private final StudentRepository studentRepository;

    @Transactional
    public void createFinancialResponsible(CreateFinancialResponsibleDTO data) {
        Optional<Student> studentOPT = this.studentRepository.findById(data.studentId());
        if (studentOPT.isPresent()) {
            Student student = studentOPT.get();

            FinancialResponsible financialResponsible = FinancialResponsible.builder()
                    .name(data.name())
                    .email(data.email())
                    .dateOfBirth(data.dateOfBirth())
                    .cpf(data.cpf())
                    .identity(data.identity())
                    .address(data.address())
                    .workAddress(data.workAddress())
                    .occupation(data.occupation())
                    .neighborhood(data.neighborhood())
                    .numberHouse(data.numberHouse())
                    .city(data.city())
                    .phone(data.phone())
                    .state(data.state())
                    .cep(data.cep())
                    .type(data.type())
                    .createdAt(LocalDateTime.now())
                    .active(true)
                    .student(student)
                    .build();

            this.financialResponsibleRepository.save(financialResponsible);
        } else {
            throw new FinancialResponsibleNotFoundException();
        }
    }

    public FinancialResponsible findFinancialResponsibleById(Long id) {
        Optional<FinancialResponsible> family = this.financialResponsibleRepository.findById(id);
        return family.orElseThrow(FinancialResponsibleNotFoundException::new);
    }

    public List<FinancialResponsible> findAllFinancialResponsibleByStudent(Long idStudent) {
        return this.financialResponsibleRepository.findAllByStudentId(idStudent);
    }

    @Transactional
    public void updateFinancialResponsible(UpdateFinancialResponsibleDTO data) {
        FinancialResponsible financialResponsible = this.findFinancialResponsibleById(data.id());

        financialResponsible.setName(data.name());
        financialResponsible.setEmail(data.email());
        financialResponsible.setDateOfBirth(data.dateOfBirth());
        financialResponsible.setCpf(data.cpf());
        financialResponsible.setIdentity(data.identity());
        financialResponsible.setAddress(data.address());
        financialResponsible.setWorkAddress(data.workAddress());
        financialResponsible.setOccupation(data.occupation());
        financialResponsible.setNeighborhood(data.neighborhood());
        financialResponsible.setNumberHouse(data.numberHouse());
        financialResponsible.setCity(data.city());
        financialResponsible.setPhone(data.phone());
        financialResponsible.setState(data.state());
        financialResponsible.setCep(data.cep());
        financialResponsible.setType(data.type());

        this.financialResponsibleRepository.save(financialResponsible);
    }

    public void disabledFinancialResponsible(Long financialResponsibleId, Long studentId) {
        List<FinancialResponsible> financialResponsibleCheck = this.findAllFinancialResponsibleByStudent(studentId);
        if (financialResponsibleCheck.size() > 1) {
            FinancialResponsible financialResponsible = this.findFinancialResponsibleById(financialResponsibleId);
            financialResponsible.setActive(false);
            this.financialResponsibleRepository.save(financialResponsible);
        } else {
            throw new IntegritDataException("The student cannot be without a Financial Responsible member");
        }
    }
}
