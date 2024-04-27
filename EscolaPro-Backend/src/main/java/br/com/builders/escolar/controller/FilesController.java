package br.com.builders.escolar.controller;

import br.com.builders.escolar.model.enums.FileTypeForSearchEnum;
import br.com.builders.escolar.service.files.SearchFiles;
import br.com.builders.escolar.utils.GetFileExtension;
import br.com.builders.escolar.utils.GetMediaTypeByExtension;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequestMapping("files")
@RequiredArgsConstructor
public class FilesController {

    private final SearchFiles searchFiles;

    @GetMapping(path = "/get/{studentId}/{fileType}")
    public ResponseEntity<Resource> searchFile(@PathVariable long studentId, @PathVariable FileTypeForSearchEnum fileType) throws IOException {
        Resource resource = this.searchFiles.findFile(studentId, fileType);
        String filename = resource.getFilename();

        if (filename == null) {
            throw new FileNotFoundException("File not found for the given id and type.");
        }

        String fileExtension = GetFileExtension.getExtension(filename);
        MediaType mediaType = GetMediaTypeByExtension.getMediaType(fileExtension);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Expose-Headers", "Content-Disposition");
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"");

       return ResponseEntity.ok()
               .headers(headers)
               .contentLength(resource.contentLength())
               .contentType(mediaType)
               .body(resource);
    }
}
