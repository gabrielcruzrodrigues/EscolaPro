package br.com.builders.escolar.exception.customized;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException() {
        super("User not found.");
    }
}
