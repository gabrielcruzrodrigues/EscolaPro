package br.com.builders.escolar.exception.customized;

public class ExtensionNotValidException extends RuntimeException {
    public ExtensionNotValidException() {
        super("Extension not valid.");
    }
}
