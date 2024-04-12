package br.com.builders.escolar.utils;

import br.com.builders.escolar.exception.customized.FileNullContentException;
import org.springframework.web.multipart.MultipartFile;

public class CheckFileType {

    public static Boolean verifyIfIsAImage(MultipartFile file) throws FileNullContentException {
        return isImage(file);
    }

    private static boolean isImage(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        if (file != null) {
            return fileName.endsWith(".JPG") || fileName.endsWith(".jpg") || fileName.endsWith("jpeg") || fileName.endsWith(".png");
        } else {
            throw new FileNullContentException();
        }
    }

    public static boolean verifyIfIsAFile(MultipartFile file) throws FileNullContentException {
        return isFile(file);
    }

    private static Boolean isFile(MultipartFile file) throws FileNullContentException {
        String fileName = file.getOriginalFilename();
        if (file != null) {
            return fileName.endsWith(".pdf");
        } else {
            throw new FileNullContentException();
        }
    }
}

