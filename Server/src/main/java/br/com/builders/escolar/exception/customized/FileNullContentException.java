package br.com.builders.escolar.exception.customized;

public class FileNullContentException extends RuntimeException {
    public FileNullContentException() {
        super("the file is null");
    }
}
