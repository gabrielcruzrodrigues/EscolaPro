package br.com.builders.escolar.utils;

public class VerifyIfIsNotNullOfEmpty {

    public static boolean isNotNullOrEmpty(String value) {
        return value != null && !value.isEmpty();
    }
}
