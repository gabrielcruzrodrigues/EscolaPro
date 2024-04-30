package br.com.builders.escolar.utils;

import br.com.builders.escolar.exception.customized.FileNullContentException;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class CheckFileType {

    public Boolean verifyIfIsAImage(MultipartFile file) throws FileNullContentException {
        return isImage(file);
    }

    private boolean isImage(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        if (file != null) {
            return fileName.endsWith(".JPG") || fileName.endsWith(".jpg") || fileName.endsWith("jpeg") || fileName.endsWith(".png");
        } else {
            throw new FileNullContentException();
        }
    }

    public boolean verifyIfIsAFile(MultipartFile file) throws FileNullContentException {
        return isFile(file);
    }

    private Boolean isFile(MultipartFile file) throws FileNullContentException {
        if (file != null) {
            String fileName = file.getOriginalFilename();
            return fileName.endsWith(".pdf");
        } else {
            throw new FileNullContentException();
        }
    }
}

