package br.com.builders.escolar.service.files;

import br.com.builders.escolar.exception.customized.FileTypeErrorException;
import br.com.builders.escolar.exception.customized.SaveFileErrorException;
import br.com.builders.escolar.model.enums.FileTypeEnum;
import br.com.builders.escolar.model.files.BasicFilesStudents;
import br.com.builders.escolar.model.files.ImagesStudents;
import br.com.builders.escolar.model.student.Student;
import br.com.builders.escolar.repository.FilesStudentRepository;
import br.com.builders.escolar.repository.ImageStudentRepository;
import br.com.builders.escolar.utils.CheckFileType;
import br.com.builders.escolar.utils.GenerateNewName;
import br.com.builders.escolar.utils.GenerateRegister;
import br.com.builders.escolar.utils.ToAddTypeFile;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class FilesStudentService implements FileStorageServiceInterface<Student>{

    private final FilesStudentRepository filesStudentRepository;
    private final ImageStudentRepository imageStudentRepository;

    @Value("${files-students-path}")
    private String filesPath;
    @Value("${images-students-path}")
    private String imagesPath;
    private String finalPath;

    @Transactional
    @Override
    public void saveFile(MultipartFile file, Student student, FileTypeEnum type) {
        if (this.verifyFileType(file)) {
            if (student != null) {
                if (type == FileTypeEnum.IMAGE) {
                    this.finalPath = this.imagesPath;
                } else {
                    this.finalPath = this.filesPath;
                }

                try {
                    byte[] bytes = file.getBytes();
                    String newFileName = this.generateFileName(file, student, type);
                    Path path = Paths.get(finalPath + "/" + newFileName);
                    Files.write(path, bytes);
                    this.saveFileReferenceInDatabase(newFileName, student, type);
                } catch (Exception ex) {
                    throw new SaveFileErrorException(ex.getMessage());
                }
            }
        }
    }

    @Override
    public boolean verifyFileType(MultipartFile file) {
        return CheckFileType.verifyIfIsAFile(file) || CheckFileType.verifyIfIsAImage(file);
    }

    @Override
    public void saveFileReferenceInDatabase(String fileName, Student student, FileTypeEnum typeForVerify) {
        String register = GenerateRegister.newRegister();
        BasicFilesStudents file = new BasicFilesStudents();

        switch (typeForVerify) {
            case IMAGE:
                ImagesStudents fileImage = ImagesStudents.builder()
                        .register(register)
                        .reference(fileName)
                        .type(FileTypeEnum.IMAGE)
                        .student(student)
                        .build();
                this.imageStudentRepository.save(fileImage);
                break;
            case RG:
                file = BasicFilesStudents.builder()
                        .register(register)
                        .reference(fileName)
                        .type(FileTypeEnum.RG)
                        .rgFile(student)
                        .build();
                this.filesStudentRepository.save(file);
                break;
            case CPF:
                file = BasicFilesStudents.builder()
                        .register(register)
                        .reference(fileName)
                        .type(FileTypeEnum.CPF)
                        .cpfFile(student)
                        .build();
                this.filesStudentRepository.save(file);
                break;
            case PROOF_OF_ADDRESS:
                file = BasicFilesStudents.builder()
                        .register(register)
                        .reference(fileName)
                        .type(FileTypeEnum.PROOF_OF_ADDRESS)
                        .proofOfAddress(student)
                        .build();
                this.filesStudentRepository.save(file);
                break;
            default:
                throw new FileTypeErrorException();
        }
    }

    @Override
    public String generateFileName(MultipartFile file, Student student, FileTypeEnum type) {
        String fileName = GenerateNewName.generateFileName(file, student.getName());
        String newFileName = ToAddTypeFile.toAddType(fileName, type);
        if (filesStudentRepository.findByReference(newFileName).isEmpty()) {
            return newFileName;
        } else {
            return GenerateNewName.addCharactersToFileName(newFileName);
        }
    }
}
