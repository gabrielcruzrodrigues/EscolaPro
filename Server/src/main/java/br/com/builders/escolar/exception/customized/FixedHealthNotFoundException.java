package br.com.builders.escolar.exception.customized;

public class FixedHealthNotFoundException extends RuntimeException {
    public FixedHealthNotFoundException() {
        super("Fixed Health not found.");
    }
}
