package br.com.builders.escolar.service.files;

import br.com.builders.escolar.exception.customized.FamilyNotFoundException;
import br.com.builders.escolar.exception.customized.FileTypeErrorException;
import br.com.builders.escolar.exception.customized.StudentNotFoundException;
import br.com.builders.escolar.model.enums.FileTypeForSearchEnum;
import br.com.builders.escolar.model.student.Student;
import br.com.builders.escolar.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SearchFiles {

    @Value("${files-students-path}")
    private String filesStudentPath;
    @Value("${images-students-path}")
    private String imagesStudentPath;
    @Value("${files-family-path}")
    private String filesFamilyPath;
    @Value("${files-financialResponsible-path}")
    private String filesFinancialResponsiblePath;

    private final StudentRepository studentRepository;

    public Resource findFile(long studentId, FileTypeForSearchEnum type) throws FileNotFoundException {
        String path;
        Optional<Student> studentOPT = this.studentRepository.findById(studentId);
        if (studentOPT.isPresent()) {
            Student student = studentOPT.get();

            switch (type) {
                case IMAGE_PROFILE:
                    path = imagesStudentPath + "/" + student.getImageProfile().getReference();
                    break;
                case RG:
                    path = filesStudentPath + "/" + student.getRgFile().getReference();
                    break;
                case CPF:
                    path = filesStudentPath + "/" + student.getCpfFile().getReference();
                    break;
                case PROOF_OF_ADDRESS:
                    path = filesStudentPath + "/" + student.getProofOfAddressFile().getReference();
                    break;
                case FAMILY_RG:
                    path = filesFamilyPath + "/" + student.getFamily().get(0).getRgFile().getReference();
                    break;
                case FAMILY_CPF:
                    path = filesFamilyPath + "/" + student.getFamily().get(0).getCpfFile().getReference();
                    break;
                case FAMILY_PROOF_OF_ADDRESS:
                    path = filesFamilyPath + "/" + student.getFamily().get(0).getProofOfAddressFile().getReference();
                    break;
                case FAMILY2_RG:
                    if (this.verifyIfFamily2Exists(student)) {
                        path = filesFamilyPath + "/" + student.getFamily().get(1).getRgFile().getReference();
                        break;
                    }
                case FAMILY2_CPF:
                    if (this.verifyIfFamily2Exists(student)) {
                        path = filesFamilyPath + "/" + student.getFamily().get(1).getCpfFile().getReference();
                        break;
                    }
                case FAMILY2_PROOF_OF_ADDRESS:
                    if (this.verifyIfFamily2Exists(student)) {
                        path = filesFamilyPath + "/" + student.getFamily().get(1).getProofOfAddressFile().getReference();
                        break;
                    }
                case FINANCIAL_RG:
                    path = filesFinancialResponsiblePath + "/" + student.getFinancialResponsible().getRgFile().getReference();
                    break;
                case FINANCIAL_CPF:
                    path = filesFinancialResponsiblePath + "/" + student.getFinancialResponsible().getCpfFile().getReference();
                    break;
                case FINANCIAL_PROOF_OF_ADDRESS:
                    path = filesFinancialResponsiblePath + "/" + student.getFinancialResponsible().getProofOfAddressFile().getReference();
                    break;
                default:
                    throw new FileTypeErrorException();
            }

            Path filePath = Paths.get(path);
            if (!Files.exists(filePath)) {
                throw new FileNotFoundException();
            }

            return new FileSystemResource(filePath);
        } else {
            throw new StudentNotFoundException();
        }
    }

    private boolean verifyIfFamily2Exists(Student student) {
        if (student.getFamily().get(1) != null) {
            return true;
        } else {
            throw new FamilyNotFoundException();
        }
    }

}
