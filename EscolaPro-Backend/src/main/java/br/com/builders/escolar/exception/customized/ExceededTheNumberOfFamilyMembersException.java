package br.com.builders.escolar.exception.customized;

public class ExceededTheNumberOfFamilyMembersException extends RuntimeException {
    public ExceededTheNumberOfFamilyMembersException() {
        super("exceeded the number of family members.");
    }
}
