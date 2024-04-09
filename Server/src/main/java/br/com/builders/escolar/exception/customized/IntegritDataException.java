package br.com.builders.escolar.exception.customized;

public class IntegritDataException extends RuntimeException {
    public IntegritDataException(String error) {
        super(error);
    }
}
