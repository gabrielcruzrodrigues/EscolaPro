package br.com.builders.escolar.service.files;

import br.com.builders.escolar.exception.customized.FileTypeErrorException;
import br.com.builders.escolar.exception.customized.SaveFileErrorException;
import br.com.builders.escolar.model.enums.FileTypeEnum;
import br.com.builders.escolar.model.files.BasicFilesFinancialResponsible;
import br.com.builders.escolar.model.student.FinancialResponsible;
import br.com.builders.escolar.repository.FilesFinancialResponsibleRepository;
import br.com.builders.escolar.utils.CheckFileType;
import br.com.builders.escolar.utils.GenerateNewName;
import br.com.builders.escolar.utils.GenerateRegister;
import br.com.builders.escolar.utils.ToAddTypeFile;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class FilesFinancialResponsibleService implements FileStorageServiceInterface<FinancialResponsible>{

    @Value("${files-financialResponsible-path}")
    private String financialResponsiblePath;

    private final FilesFinancialResponsibleRepository filesFinancialResponsibleRepository;
    private final CheckFileType checkFileType;

    @Override
    public void saveFile(MultipartFile file, FinancialResponsible financialResponsible, FileTypeEnum type) {
        if (checkFileType.verifyIfIsAFile(file)) {
            if (financialResponsible != null) {
                try {
                    byte[] bytes = file.getBytes();
                    String newFileName = this.generateFileName(file, financialResponsible, type);
                    Path path = Paths.get(financialResponsiblePath + "/" + newFileName);
                    Files.write(path, bytes);
                    this.saveFileReferenceInDatabase(newFileName, financialResponsible, type);
                } catch (Exception ex) {
                    throw new SaveFileErrorException(ex.getMessage());
                }
            }
        }
    }

    @Override
    public void saveFileReferenceInDatabase(String fileName, FinancialResponsible financialResponsible, FileTypeEnum typeForVerify) {
        String register = GenerateRegister.newRegister();
        BasicFilesFinancialResponsible file = new BasicFilesFinancialResponsible();

        switch (typeForVerify) {
            case RG:
                file = BasicFilesFinancialResponsible.builder()
                        .register(register)
                        .reference(fileName)
                        .type(FileTypeEnum.RG)
                        .rgFile(financialResponsible)
                        .build();
                this.filesFinancialResponsibleRepository.save(file);
                break;
            case CPF:
                file = BasicFilesFinancialResponsible.builder()
                        .register(register)
                        .reference(fileName)
                        .type(FileTypeEnum.CPF)
                        .cpfFile(financialResponsible)
                        .build();
                this.filesFinancialResponsibleRepository.save(file);
                break;
            case PROOF_OF_ADDRESS:
                file = BasicFilesFinancialResponsible.builder()
                        .register(register)
                        .reference(fileName)
                        .type(FileTypeEnum.PROOF_OF_ADDRESS)
                        .proofOfAddress(financialResponsible)
                        .build();
                this.filesFinancialResponsibleRepository.save(file);
                break;
            default:
                throw new FileTypeErrorException();
        }
    }

    @Override
    public String generateFileName(MultipartFile file, FinancialResponsible financialResponsible, FileTypeEnum type) {
        String fileName = GenerateNewName.generateFileName(file, financialResponsible.getName());
        String newFileName = ToAddTypeFile.toAddType(fileName, type);
        if (filesFinancialResponsibleRepository.findByReference(newFileName).isEmpty()) {
            return newFileName;
        } else {
            return GenerateNewName.addCharactersToFileName(newFileName);
        }
    }
}
