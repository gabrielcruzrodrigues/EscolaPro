package br.com.builders.escolar.model.DTO;

import org.springframework.core.io.Resource;

public record ResourceWithFilenameDTO(
        Resource resource,
        String filename
) {
}
