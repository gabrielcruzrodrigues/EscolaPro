package br.com.builders.escolar.exception.customized;

public class SaveFileErrorException extends RuntimeException {
    public SaveFileErrorException(String error) {
        super(error);
    }
}
