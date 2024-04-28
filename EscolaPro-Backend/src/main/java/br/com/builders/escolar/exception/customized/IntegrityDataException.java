package br.com.builders.escolar.exception.customized;

public class IntegrityDataException extends RuntimeException {
    public IntegrityDataException(String error) {
        super(error);
    }
}
