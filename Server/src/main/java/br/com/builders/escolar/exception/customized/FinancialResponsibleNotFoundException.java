package br.com.builders.escolar.exception.customized;

public class FinancialResponsibleNotFoundException extends RuntimeException {
    public FinancialResponsibleNotFoundException() {
        super("Financial responsible not found.");
    }
}
