package br.com.builders.escolar.utils;

import org.springframework.http.MediaType;

public class GetMediaTypeByExtension {

    public static MediaType getMediaType(String extension) {
        return switch (extension.toLowerCase()) {
            case "pdf" -> MediaType.APPLICATION_PDF;
            case "png" -> MediaType.IMAGE_PNG;
            case "jpg", "jpeg" -> MediaType.IMAGE_JPEG;
            default -> MediaType.APPLICATION_OCTET_STREAM;
        };
    }
}
