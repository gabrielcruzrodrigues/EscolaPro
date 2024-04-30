package br.com.builders.escolar.service.files;

import br.com.builders.escolar.exception.customized.FamilyNotFoundException;
import br.com.builders.escolar.exception.customized.FileNullContentException;
import br.com.builders.escolar.exception.customized.FileTypeErrorException;
import br.com.builders.escolar.exception.customized.SaveFileErrorException;
import br.com.builders.escolar.model.enums.FileTypeEnum;
import br.com.builders.escolar.model.files.BasicFilesFamily;
import br.com.builders.escolar.model.files.BasicFilesStudents;
import br.com.builders.escolar.model.student.Family;
import br.com.builders.escolar.repository.FilesFamilyRepository;
import br.com.builders.escolar.utils.CheckFileType;
import br.com.builders.escolar.utils.GenerateNewName;
import lombok.ToString;
import org.junit.jupiter.api.AfterEach;
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

    private MockedStatic<CheckFileType> mockedStaticCheckFileType;

    @AfterEach
    void tearDown() {
        if (mockedStaticCheckFileType != null) {
            mockedStaticCheckFileType.close();
        }
    }

    @Test
    @DisplayName("Must receive data and save file with success")
    void saveFile_withSuccess() throws IOException {
        //configuration test
        byte[] content = "file content".getBytes();
        FileTypeEnum type = FileTypeEnum.RG;
        String expectedFileName = "document.pdf";
        Path expectedPath = Paths.get("files/family/" + expectedFileName);

        try (MockedStatic<Files> mockedFiles = Mockito.mockStatic(Files.class)) {
            MockedStatic<CheckFileType> mockedStatic_CheckFileType = Mockito.mockStatic(CheckFileType.class);
            MockedStatic<GenerateNewName> mockedStatic_GenerateFileName = Mockito.mockStatic(GenerateNewName.class);

            when(file.getBytes()).thenReturn(content);
            when(filesFamilyRepository.findByReference(anyString())).thenReturn(Optional.empty());
            when(family.getName()).thenReturn("Gabriel");

            mockedStatic_GenerateFileName.when(() -> GenerateNewName.generateFileName(any(MultipartFile.class), anyString()))
                    .thenReturn(expectedFileName);
            mockedStatic_CheckFileType.when(() -> CheckFileType.verifyIfIsAFile(any(MultipartFile.class)))
                    .thenReturn(true);
            mockedFiles.when(() -> Files.write(eq(expectedPath), eq(content))).thenReturn(null);

            //action
            filesFamilyService.saveFile(file, family, type);

            //verify
            verify(file, times(1)).getBytes();
            mockedStatic_GenerateFileName.verify(() -> GenerateNewName.generateFileName(any(MultipartFile.class), anyString()), times(1));
            mockedStatic_CheckFileType.verify(() -> CheckFileType.verifyIfIsAFile(file), times(1));
        }
    }

    @Test
    @DisplayName("Must return FileNullContentException when to call verifyFileType")
    void saveFile_withSuccess_FileNullContentException() {
        //configuration test
        FileTypeEnum type = FileTypeEnum.RG;
        try (MockedStatic<CheckFileType> mockedStatic_CheckFileType = Mockito.mockStatic(CheckFileType.class)) {
            mockedStatic_CheckFileType.when(() -> CheckFileType.verifyIfIsAFile(any(MultipartFile.class))).thenReturn(false);

            //action
            FileNullContentException response = assertThrows(FileNullContentException.class, () -> {
                filesFamilyService.saveFile(file, family, type);
            }, "Must capture FileNullContentException");

            //verify
            assertEquals(FileNullContentException.class, response.getClass());
        }
    }

    @Test
    @DisplayName("Must return FamilyNotFoundException when family equals null")
    void saveFile_withSuccess_FamilyNotFoundException() {
        //configuration test
        FileTypeEnum type = FileTypeEnum.RG;
        try (MockedStatic<CheckFileType> mockedStatic_CheckFileType = Mockito.mockStatic(CheckFileType.class)) {
            mockedStatic_CheckFileType.when(() -> CheckFileType.verifyIfIsAFile(any(MultipartFile.class))).thenReturn(true);

            //action
            FamilyNotFoundException response = assertThrows(FamilyNotFoundException.class, () -> {
                filesFamilyService.saveFile(file, null, type);
            }, "Must capture FamilyNotFoundException");

            //verify
            assertEquals(FamilyNotFoundException.class, response.getClass());
        }
    }

    @Test
    @DisplayName("Must receive SaveFileErrorException when Files.write try save file")
    void saveFile_SaveFileErrorException() throws IOException {
        //configuration test
        byte[] content = "file content".getBytes();
        FileTypeEnum type = FileTypeEnum.RG;
        String expectedFileName = "document.pdf";
        Path expectedPath = Paths.get("files/family/" + expectedFileName);

        try (MockedStatic<CheckFileType> mockedStatic_CheckFileType = Mockito.mockStatic(CheckFileType.class)) {
            MockedStatic<Files> mockedFiles = Mockito.mockStatic(Files.class);
            MockedStatic<GenerateNewName> mockedStatic_GenerateFileName = Mockito.mockStatic(GenerateNewName.class);

            when(file.getBytes()).thenReturn(content);
            when(filesFamilyRepository.findByReference(anyString())).thenReturn(Optional.empty());
            when(family.getName()).thenReturn("Gabriel");

            mockedStatic_GenerateFileName.when(() -> GenerateNewName.generateFileName(any(MultipartFile.class), anyString()))
                    .thenReturn(expectedFileName);
            mockedStatic_CheckFileType.when(() -> CheckFileType.verifyIfIsAFile(any(MultipartFile.class)))
                    .thenReturn(true);
            mockedFiles.when(() -> Files.write(eq(expectedPath), eq(content))).thenThrow(new IOException("Simulated IO Exception"));

            //action
            try {
                filesFamilyService.saveFile(file, family, type);
            } catch (SaveFileErrorException ex) {

                //verify
                assertNotNull(ex);
                assertTrue(ex.getCause() instanceof IOException);
                assertEquals("Simulated IO Exception", ex.getCause().getMessage());
            }
        }
    }

    @Test
    @DisplayName("Must return true in verify type file")
    void verifyFileType_true() {
        try(MockedStatic<CheckFileType> mockedStatic = Mockito.mockStatic(CheckFileType.class)) {
            mockedStatic.when(() -> CheckFileType.verifyIfIsAFile(file)).thenReturn(true);

            boolean response = filesFamilyService.verifyFileType(file);

            assertTrue(response);
            mockedStatic.verify(() -> CheckFileType.verifyIfIsAFile(file), times(1));
        }
    }

    @Test
    @DisplayName("Must return a false when file is not a png")
    void verifyFileType_false() {
        when(file.getOriginalFilename()).thenReturn("file.png");

        boolean response = filesFamilyService.verifyFileType(file);

        assertFalse(response);
        verify(file, times(1)).getOriginalFilename();
    }

    @Test
    @DisplayName("Must save file reference in database with success")
    void saveFileReferenceInDatabase_withSuccess() {
        FileTypeEnum type = FileTypeEnum.RG;
        filesFamilyService.saveFileReferenceInDatabase("filename.pdf", family, type);

        verify(filesFamilyRepository, times(1)).save(any(BasicFilesFamily.class));
    }

    @Test
    @DisplayName("Must return FileTypeErrorException when the type file no match with options")
    void saveFileReferenceInDatabase_FileTypeErrorException() {
        FileTypeEnum type = FileTypeEnum.FILE;
        FileTypeErrorException response = assertThrows(FileTypeErrorException.class, () -> {
            filesFamilyService.saveFileReferenceInDatabase("filename.pdf", family, type);
        }, "Must capture FileTypeErrorException");

        assertEquals(FileTypeErrorException.class, response.getClass());
    }

    @Test
    @DisplayName("Must return a new file name with success")
    void generateFileName_withSuccess() {
        FileTypeEnum type = FileTypeEnum.RG;
        when(file.getOriginalFilename()).thenReturn("filename.pdf");
        when(filesFamilyRepository.findByReference(anyString())).thenReturn(Optional.empty());

        String response = filesFamilyService.generateFileName(file, family, type);

        assertNotNull(response);
        assertEquals(String.class, response.getClass());
        verify(file, times(1)).getOriginalFilename();
        verify(filesFamilyRepository, times(1)).findByReference(anyString());
    }

    @Test
    @DisplayName("Must additing new caracters to new file name if exists filename in database")
    void generateFileName_addCharactersToFileName() {
        FileTypeEnum type = FileTypeEnum.RG;
        when(file.getOriginalFilename()).thenReturn("filename.pdf");
        when(filesFamilyRepository.findByReference(anyString())).thenReturn(Optional.of(new BasicFilesFamily()));

        String response = filesFamilyService.generateFileName(file, family, type);

        assertNotNull(response);
        assertEquals(String.class, response.getClass());
        verify(file, times(1)).getOriginalFilename();
        verify(filesFamilyRepository, times(1)).findByReference(anyString());
    }
}