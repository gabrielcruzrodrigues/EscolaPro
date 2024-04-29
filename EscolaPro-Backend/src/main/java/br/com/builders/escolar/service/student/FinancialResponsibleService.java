package br.com.builders.escolar.service.student;

import br.com.builders.escolar.exception.customized.FinancialResponsibleNotFoundException;
import br.com.builders.escolar.exception.customized.IntegrityDataException;
import br.com.builders.escolar.exception.customized.StudentNotFoundException;
import br.com.builders.escolar.model.DTO.CreateFinancialResponsibleDTO;
import br.com.builders.escolar.model.DTO.UpdateFinancialResponsibleDTO;
import br.com.builders.escolar.model.enums.FileTypeEnum;
import br.com.builders.escolar.model.student.FinancialResponsible;
import br.com.builders.escolar.model.student.Student;
import br.com.builders.escolar.repository.FinancialResponsibleRepository;
import br.com.builders.escolar.repository.StudentRepository;
import br.com.builders.escolar.service.files.FilesFinancialResponsibleService;
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
    private final FilesFinancialResponsibleService filesFinancialResponsibleService;
    private final StudentRepository studentRepository;

    @Transactional
    public void createFinancialResponsible(CreateFinancialResponsibleDTO data) {
        Optional<Student> studentOPT = this.studentRepository.findById(data.studentId());
        if (studentOPT.isPresent()) {
            Student student = studentOPT.get();
            FinancialResponsible financialResponsible = modelingNewFinancialResponsible(data, student);
            FinancialResponsible response = this.financialResponsibleRepository.save(financialResponsible);
            this.saveFiles(data, response);
        } else {
            throw new StudentNotFoundException();
        }
    }

    private FinancialResponsible modelingNewFinancialResponsible(CreateFinancialResponsibleDTO data, Student student) {
        FinancialResponsible financialResponsible = new FinancialResponsible();
        financialResponsible.setName(data.name());
        financialResponsible.setIdentity(data.identity());
        financialResponsible.setCpf(data.cpf());
        financialResponsible.setDateOfBirth(data.dateOfBirth());
        financialResponsible.setNationality(data.nationality());
        financialResponsible.setNaturalness(data.naturalness());
        financialResponsible.setEmail(data.email());
        financialResponsible.setCep(data.cep());
        financialResponsible.setAddress(data.address());
        financialResponsible.setPhone(data.phone());
        financialResponsible.setNeighborhood(data.neighborhood());
        financialResponsible.setNumberHouse(data.numberHouse());
        financialResponsible.setCity(data.city());
        financialResponsible.setState(data.state());
        financialResponsible.setCountry(data.country());
        financialResponsible.setWorkAddress(data.workAddress());
        financialResponsible.setOccupation(data.occupation());
        financialResponsible.setType(data.type());
        financialResponsible.setCreatedAt(LocalDateTime.now());
        financialResponsible.setActive(true);
        financialResponsible.setStudent(student);
        return financialResponsible;
    }

    @Transactional
    private void saveFiles(CreateFinancialResponsibleDTO data, FinancialResponsible financialResponsible) {
        if(data.rgFile() != null) {
            this.filesFinancialResponsibleService.saveFile(data.rgFile(), financialResponsible, FileTypeEnum.RG);
        }
        if(data.cpfFile() != null) {
            this.filesFinancialResponsibleService.saveFile(data.cpfFile(), financialResponsible, FileTypeEnum.CPF);
        }
        if(data.proofOfAddress() != null) {
            this.filesFinancialResponsibleService.saveFile(data.proofOfAddress(), financialResponsible, FileTypeEnum.PROOF_OF_ADDRESS);
        }
    }

    public FinancialResponsible findFinancialResponsibleById(Long id) {
        Optional<FinancialResponsible> financialResponsible = this.financialResponsibleRepository.findById(id);
        return financialResponsible.orElseThrow(FinancialResponsibleNotFoundException::new);
    }

//    public List<FinancialResponsible> findAllFinancialResponsibleByStudent(Long idStudent) {
//
//        return this.financialResponsibleRepository.findAllByStudentId(idStudent);
//    }

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

//    public void disabledFinancialResponsible(Long financialResponsibleId, Long studentId) {
//        List<FinancialResponsible> financialResponsibleCheck = this.findAllFinancialResponsibleByStudent(studentId);
//        if (financialResponsibleCheck.size() > 1) {
//            FinancialResponsible financialResponsible = this.findFinancialResponsibleById(financialResponsibleId);
//            financialResponsible.setActive(false);
//            this.financialResponsibleRepository.save(financialResponsible);
//        } else {
//            throw new IntegrityDataException("The student cannot be without a Financial Responsible member");
//        }
//    }
}
