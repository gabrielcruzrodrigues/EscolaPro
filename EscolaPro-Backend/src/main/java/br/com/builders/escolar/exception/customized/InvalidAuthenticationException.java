package br.com.builders.escolar.exception.customized;

public class InvalidAuthenticationException extends RuntimeException {
    public InvalidAuthenticationException(String error) {
        super(error);
    }
}
