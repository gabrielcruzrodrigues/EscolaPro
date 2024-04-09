package br.com.builders.escolar.exception.customized;

public class FamilyNotFoundException extends RuntimeException {
    public FamilyNotFoundException() {
        super("Family not found.");
    }
}
