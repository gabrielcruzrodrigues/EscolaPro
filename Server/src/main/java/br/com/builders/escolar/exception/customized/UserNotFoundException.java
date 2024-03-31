package br.com.builders.escolar.exception.customized;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User not found exception!");
    }
}
