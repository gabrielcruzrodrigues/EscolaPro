package br.com.builders.escolar.service.student;

import br.com.builders.escolar.exception.customized.FileTypeErrorException;
import br.com.builders.escolar.exception.customized.SaveFileErrorException;
import br.com.builders.escolar.model.enums.FileTypeEnum;
import br.com.builders.escolar.model.files.BasicFilesStudents;
import br.com.builders.escolar.model.files.ImagesStudents;
import br.com.builders.escolar.model.student.Student;
import br.com.builders.escolar.repository.FilesStudentRepository;
import br.com.builders.escolar.repository.ImageStudentRepository;
import br.com.builders.escolar.repository.StudentRepository;
import br.com.builders.escolar.utils.CheckFileType;
import br.com.builders.escolar.utils.GenerateNewName;
import br.com.builders.escolar.utils.GenerateRegister;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FilesStudentService {

    private final StudentRepository studentRepository;
    private final FilesStudentRepository filesStudentRepository;
    private final ImageStudentRepository imageStudentRepository;

    @Value("${files-students-path}")
    private String filesPath;

    public void saveFile(MultipartFile file, Long studentId, FileTypeEnum type) {
        if (CheckFileType.verifyIfIsAFile(file)) {
            Optional<Student> studentOPT = studentRepository.findById(studentId);
            if (studentOPT.isPresent()) {
                Student student = studentOPT.get();

                try {
                    byte[] bytes = file.getBytes();
                    String newFileName = this.generateFileName(file, student);
                    Path path = Paths.get(filesPath + "/" + newFileName);
                    Files.write(path, bytes);
                    this.saveFileReferenceInDatabase(newFileName, student, type);
                } catch (Exception ex) {
                    throw new SaveFileErrorException(ex.getMessage());
                }
            }
        }
    }

    private void saveFileReferenceInDatabase(String fileName, Student student, FileTypeEnum typeForVerify) {
        String register = GenerateRegister.newRegister();

        FileTypeEnum type = switch (typeForVerify) {
            case CPF -> FileTypeEnum.CPF;
            case RG -> FileTypeEnum.RG;
            case PROOF_OF_ADDRESS -> FileTypeEnum.PROOF_OF_ADDRESS;
            case IMAGE -> FileTypeEnum.IMAGE;
            default -> throw new FileTypeErrorException();
        };

        if (type == FileTypeEnum.IMAGE) {
            ImagesStudents fileImage = ImagesStudents.builder()
                    .register(register)
                    .reference(fileName)
                    .type(type)
                    .student(student)
                    .build();

            this.imageStudentRepository.save(fileImage);

        } else {
            BasicFilesStudents file = BasicFilesStudents.builder()
                    .register(register)
                    .reference(fileName)
                    .type(type)
                    .student(student)
                    .build();

            this.filesStudentRepository.save(file);
        }
    }

    private String generateFileName(MultipartFile file, Student student) {
        String newFileName = GenerateNewName.generateFileName(file, student);
        if (filesStudentRepository.findByReference(newFileName).isEmpty()) {
            return newFileName;
        } else {
            return GenerateNewName.addCharactersToFileName(newFileName);
        }
    }
}
