package br.com.builders.escolar.exception.customized;

import java.io.File;

public class FileTypeErrorException extends RuntimeException {
    public FileTypeErrorException() {
        super("Invalid file type.");
    }
}
