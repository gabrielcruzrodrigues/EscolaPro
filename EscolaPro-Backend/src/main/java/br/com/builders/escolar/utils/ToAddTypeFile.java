package br.com.builders.escolar.utils;

import br.com.builders.escolar.model.enums.FileTypeEnum;

public class ToAddTypeFile {

    public static String toAddType(String filename, FileTypeEnum type) {
        return filename = type.toString() + "_" + filename;
    }
}
