package br.com.builders.escolar.service.files;

import br.com.builders.escolar.model.enums.FileTypeEnum;
import br.com.builders.escolar.model.files.BasicFilesFamily;
import br.com.builders.escolar.model.student.Family;
import br.com.builders.escolar.repository.FilesFamilyRepository;
import br.com.builders.escolar.utils.CheckFileType;
import br.com.builders.escolar.utils.GenerateNewName;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FilesFamilyServiceTest {

    @Mock
    private FilesFamilyRepository filesFamilyRepository;

    @Mock
    private MultipartFile file;

    @Mock
    private Family family;

    @InjectMocks
    private FilesFamilyService filesFamilyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Must receive data and save file with success")
    void saveFile_withSuccess() throws IOException {
        //configuration test
        byte[] content = "file content".getBytes();
        FileTypeEnum type = FileTypeEnum.RG;
        String expectedFileName = "document.pdf";
        Path expectedPath = Paths.get("files/family/" + expectedFileName);

        try(MockedStatic<Files> mockedFiles = Mockito.mockStatic(Files.class)) {
            MockedStatic<CheckFileType> mockedStatic_CheckFileType = Mockito.mockStatic(CheckFileType.class);
            MockedStatic<GenerateNewName> mockedStatic_GenerateFileName = Mockito.mockStatic(GenerateNewName.class);

            when(file.getBytes()).thenReturn(content);
            when(filesFamilyRepository.findByReference(anyString())).thenReturn(Optional.empty());
            mockedStatic_GenerateFileName.when(() -> GenerateNewName.generateFileName(any(MultipartFile.class), anyString()))
                    .thenReturn(expectedFileName);
            mockedStatic_CheckFileType.when(() -> CheckFileType.verifyIfIsAFile(any(MultipartFile.class)))
                    .thenReturn(true);
            mockedFiles.when(() -> Files.write(eq(expectedPath), eq(content))).thenReturn(null);

            //action
            filesFamilyService.saveFile(file, family, type);

            //verify
            verify(file, times(1)).getBytes();
//            verify(filesFamilyService, times(1)).saveFileReferenceInDatabase(expectedFileName, family, type);
//            mockedFiles.verify(() -> Files.write(eq(expectedPath), eq(content)), times(1)); // Verifying if Files.write was called
        }
    }

    @Test
    void verifyFileType() {
    }

    @Test
    void saveFileReferenceInDatabase() {
    }

    @Test
    void generateFileName() {
    }
}