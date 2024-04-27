package br.com.builders.escolar.service.student;

import br.com.builders.escolar.exception.customized.ExceededTheNumberOfFamilyMembersException;
import br.com.builders.escolar.exception.customized.FamilyNotFoundException;
import br.com.builders.escolar.exception.customized.IntegritDataException;
import br.com.builders.escolar.exception.customized.StudentNotFoundException;
import br.com.builders.escolar.model.DTO.CreateFamilyStudentDTO;
import br.com.builders.escolar.model.DTO.UpdateFamilyDTO;
import br.com.builders.escolar.model.enums.FileTypeEnum;
import br.com.builders.escolar.model.student.Family;
import br.com.builders.escolar.model.student.Student;
import br.com.builders.escolar.repository.FamilyRepository;
import br.com.builders.escolar.repository.StudentRepository;
import br.com.builders.escolar.service.files.FilesFamilyService;
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
    private final FilesFamilyService filesFamilyService;

    @Transactional
    public void createFamilyByStudent(CreateFamilyStudentDTO data) {
        Optional<Student> studentOPT = this.studentRepository.findById(data.studentId());
        if (studentOPT.isPresent()) {
            if (studentOPT.get().getFamily().size() <= 1) {
                Student student = studentOPT.get();
                Family family = modelingNewFamily(data, student);
                Family response = this.familyRepository.save(family);
                this.saveFiles(data, response);
            } else {
                throw new ExceededTheNumberOfFamilyMembersException();
            }
        } else {
            throw new StudentNotFoundException();
        }
    }

    public Family modelingNewFamily(CreateFamilyStudentDTO data, Student student) {
        Family family = new Family();
        family.setName(data.name());
        family.setIdentity(data.identity());
        family.setCpf(data.cpf());
        family.setDateOfBirth(data.dateOfBirth());
        family.setNationality(data.nationality());
        family.setNaturalness(data.naturalness());
        family.setEmail(data.email());
        family.setCep(data.cep());
        family.setAddress(data.address());
        family.setPhone(data.phone());
        family.setNeighborhood(data.neighborhood());
        family.setNumberHouse(data.numberHouse());
        family.setCity(data.city());
        family.setState(data.state());
        family.setCountry(data.country());
        family.setWorkAddress(data.workAddress());
        family.setOccupation(data.occupation());
        family.setType(data.type());
        family.setCreatedAt(LocalDateTime.now());
        family.setActive(true);
        family.setStudent(student);

        return family;
    }

    @Transactional
    private void saveFiles(CreateFamilyStudentDTO data, Family family) {
        if(data.rgFile() != null) {
            this.filesFamilyService.saveFile(data.rgFile(), family, FileTypeEnum.RG);
        }
        if(data.cpfFile() != null) {
            this.filesFamilyService.saveFile(data.cpfFile(), family, FileTypeEnum.CPF);
        }
        if(data.proofOfAddress() != null) {
            this.filesFamilyService.saveFile(data.proofOfAddress(), family, FileTypeEnum.PROOF_OF_ADDRESS);
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

        family.setName(data.name());
        family.setEmail(data.email());
        family.setDateOfBirth(data.dateOfBirth());
        family.setAddress(data.address());
        family.setWorkAddress(data.workAddress());
        family.setOccupation(data.occupation());
        family.setNeighborhood(data.neighborhood());
        family.setNumberHouse(data.numberHouse());
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
