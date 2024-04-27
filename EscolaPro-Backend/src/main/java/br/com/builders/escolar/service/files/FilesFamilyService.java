package br.com.builders.escolar.service.files;

import java.nio.file.Files;
import java.nio.file.Paths;

import br.com.builders.escolar.utils.ToAddTypeFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.builders.escolar.exception.customized.FileTypeErrorException;
import br.com.builders.escolar.exception.customized.SaveFileErrorException;
import br.com.builders.escolar.model.enums.FileTypeEnum;
import br.com.builders.escolar.model.files.BasicFilesFamily;
import br.com.builders.escolar.model.student.Family;
import br.com.builders.escolar.repository.FilesFamilyRepository;
import br.com.builders.escolar.utils.CheckFileType;
import br.com.builders.escolar.utils.GenerateNewName;
import br.com.builders.escolar.utils.GenerateRegister;
import lombok.RequiredArgsConstructor;

import java.nio.file.Path;

@Service
@RequiredArgsConstructor
public class FilesFamilyService implements FileStorageServiceInterface<Family>{

     @Value("${files-family-path}")
     private String filesFamilyPath;

     private final FilesFamilyRepository filesFinancialResponsibleRepository;

     @Override
     public void saveFile(MultipartFile file, Family family, FileTypeEnum type) {
          if (verifyFileType(file)) {
               if (family != null) {
                    try {
                         byte[] bytes = file.getBytes();
                         String newFileName = this.generateFileName(file, family, type);
                         Path path = Paths.get(filesFamilyPath + "/" + newFileName);
                         Files.write(path, bytes);
                         this.saveFileReferenceInDatabase(newFileName, family, type);
                    } catch (Exception ex) {
                         throw new SaveFileErrorException(ex.getMessage());
                    }
               }
          }
     }

     @Override
     public boolean verifyFileType(MultipartFile file) {
          return CheckFileType.verifyIfIsAFile(file);
     }

     @Override
     public void saveFileReferenceInDatabase(String fileName, Family family, FileTypeEnum typeForVerify) {
          String register = GenerateRegister.newRegister();
          BasicFilesFamily file = new BasicFilesFamily();

          switch (typeForVerify) {
               case RG:
                    file = BasicFilesFamily.builder()
                            .register(register)
                            .reference(fileName)
                            .type(FileTypeEnum.RG)
                            .rgFile(family)
                            .build();
                    this.filesFinancialResponsibleRepository.save(file);
                    break;
               case CPF:
                    file = BasicFilesFamily.builder()
                            .register(register)
                            .reference(fileName)
                            .type(FileTypeEnum.CPF)
                            .cpfFile(family)
                            .build();
                    this.filesFinancialResponsibleRepository.save(file);
                    break;
               case PROOF_OF_ADDRESS:
                    file = BasicFilesFamily.builder()
                            .register(register)
                            .reference(fileName)
                            .type(FileTypeEnum.PROOF_OF_ADDRESS)
                            .proofOfAddress(family)
                            .build();
                    this.filesFinancialResponsibleRepository.save(file);
                    break;
               default:
                    throw new FileTypeErrorException();
          }
     }

     @Override
     public String generateFileName(MultipartFile file, Family family, FileTypeEnum type) {
          String fileName = GenerateNewName.generateFileName(file, family.getName());
          String newFileName = ToAddTypeFile.toAddType(fileName, type);
          if (filesFinancialResponsibleRepository.findByReference(newFileName).isEmpty()) {
               return newFileName;
          } else {
               return GenerateNewName.addCharactersToFileName(newFileName);
          }
     }
}
