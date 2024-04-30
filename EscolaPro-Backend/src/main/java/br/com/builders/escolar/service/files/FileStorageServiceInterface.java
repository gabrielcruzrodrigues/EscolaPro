package br.com.builders.escolar.service.files;

import br.com.builders.escolar.model.enums.FileTypeEnum;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageServiceInterface<T> {
    void saveFile(MultipartFile file, T entity, FileTypeEnum type);

    void saveFileReferenceInDatabase(String fileName, T entity, FileTypeEnum typeForVerify);

    String generateFileName(MultipartFile file, T entity, FileTypeEnum type);
}
