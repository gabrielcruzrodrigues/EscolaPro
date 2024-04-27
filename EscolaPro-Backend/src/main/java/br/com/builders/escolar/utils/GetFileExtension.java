package br.com.builders.escolar.utils;

import br.com.builders.escolar.exception.customized.ExtensionNotValidException;

public class GetFileExtension {

    public static String getExtension(String filename) {
        if (filename != null && filename.contains(".")) {
            return filename.substring(filename.lastIndexOf(".") + 1);
        } else {
            throw new ExtensionNotValidException();
        }
    }
}
